package com.lawencon.bookleasing.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.bookleasing.constants.Constants;
import com.lawencon.bookleasing.entity.Customer;
import com.lawencon.bookleasing.entity.Inventory;
import com.lawencon.bookleasing.entity.Profile;
import com.lawencon.bookleasing.entity.RentalDetail;
import com.lawencon.bookleasing.entity.RentalHeader;
import com.lawencon.bookleasing.entity.User;
import com.lawencon.bookleasing.error.DataIsNotExistsException;
import com.lawencon.bookleasing.model.TransactionDetailRequest;
import com.lawencon.bookleasing.model.TransactionDetailResponse;
import com.lawencon.bookleasing.model.TransactionRequest;
import com.lawencon.bookleasing.model.TransactionResponse;
import com.lawencon.bookleasing.service.CustomerService;
import com.lawencon.bookleasing.service.InventoryService;
import com.lawencon.bookleasing.service.RentalDetailService;
import com.lawencon.bookleasing.service.RentalHeaderService;
import com.lawencon.bookleasing.service.TransactionService;
import com.lawencon.bookleasing.service.UserService;

/**
 * @author Rian Rivaldo
 */
@Service
public class TransactionServiceImpl implements TransactionService {

  @Autowired
  private UserService userService;

  @Autowired
  private InventoryService inventoryService;

  @Autowired
  private CustomerService customerService;

  @Autowired
  private RentalHeaderService headerService;

  @Autowired
  private RentalDetailService detailService;

  @Transactional
  @Override
  public String addRentalTransaction(String data) throws Exception {
	ObjectMapper objMapper = new ObjectMapper();
	TransactionRequest request = objMapper.readValue(data.getBytes(), TransactionRequest.class);

	User user = Optional
	    .ofNullable(
	        userService.getById(Objects.requireNonNull(request.getUserId(), () -> "User id must be submitted.")))
	    .orElseThrow(() -> new IllegalAccessException("Invalid user!"));

	List<TransactionDetailRequest> detailRequests = Objects.requireNonNull(request.getItems(),
	    () -> "Items must be submitted.");
	if (detailRequests.isEmpty()) {
	  throw new IllegalArgumentException("Detail transaction is empty!");
	}
	Customer customer = new Customer();
	customer.setProfile(Objects.requireNonNull(request.getCustomer(), () -> "Customer data must be submitted."));

	RentalHeader header = new RentalHeader();
	String receiptNumber = "TRX" + System.currentTimeMillis();
	header.setReceipt(receiptNumber);
	header.setCustomer(customer);
	header.setUser(user);

	LocalDateTime dateTimeNow = LocalDateTime.now();
	List<RentalDetail> detailList = new ArrayList<>();
	for (TransactionDetailRequest detailRequest : detailRequests) {
	  Inventory inventory = inventoryService
	      .getInventoryById(Objects.requireNonNull(detailRequest.getItemId(), () -> "Item id must be submitted."));
	  if (inventory.getStatus().getStatus().equalsIgnoreCase(Constants.BORROWED_STATUS)) {
		throw new IllegalArgumentException("Book is not available!");
	  }
	  RentalDetail detail = new RentalDetail();
	  detail.setInventory(inventory);
	  detail.setRentalDate(dateTimeNow);
	  detail.setReturnDate(dateTimeNow.plusDays(detailRequest.getRentalDays()));
	  detailList.add(detail);
	}

	Profile profile = header.getCustomer().getProfile();
	try {
	  Customer validatedCustomer = customerService
	      .getCustomerByEmail(Objects.requireNonNull(profile.getEmail(), () -> "Customer email must be submitted."));
	  header.setCustomer(validatedCustomer);
	} catch (DataIsNotExistsException e) {
	  customerService.create(header.getCustomer());
	}

	headerService.create(header);
	for (RentalDetail detail : detailList) {
	  detail.setRentalHeader(header);
	  detailService.create(detail);
	}
	return receiptNumber;
  }

  @Override
  public TransactionResponse getRentalTransaction(String receiptNumber) throws Exception {
	List<RentalDetail> detailList = detailService.findAllByReceiptNumber(receiptNumber);
	if (detailList.isEmpty()) {
	  throw new NoResultException("There is no transaction with recipt number : " + receiptNumber);
	}

	TransactionResponse response = new TransactionResponse();
	response.setCustomer(detailList.get(0).getRentalHeader().getCustomer().getProfile());

	List<TransactionDetailResponse> detailResponseList = new ArrayList<>();
	detailList.forEach(detail -> {
	  TransactionDetailResponse detailResponse = new TransactionDetailResponse();
	  detailResponse.setBook(detail.getInventory().getBook());
	  detailResponse.setRentalDate(detail.getRentalDate());
	  detailResponse.setReturnDate(detail.getReturnDate());
	  detailResponseList.add(detailResponse);
	});
	response.setDetailItems(detailResponseList);
	return response;
  }

}
