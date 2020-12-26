package com.lawencon.bookleasing.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import com.lawencon.bookleasing.service.RentalDetailService;
import com.lawencon.bookleasing.service.RentalHeaderService;
import com.lawencon.bookleasing.service.TransactionService;
import com.lawencon.bookleasing.service.UserService;

/**
 * @author Rian Rivaldo
 */
@Service
public class TransactionServiceImpl implements TransactionService {

  private final UserService userService;
  private final CustomerService customerService;
  private final RentalHeaderService headerService;
  private final RentalDetailService detailService;

  @Autowired
  public TransactionServiceImpl(UserService userService, CustomerService customerService,
      RentalHeaderService headerService, RentalDetailService detailService) {
	this.userService = userService;
	this.customerService = customerService;
	this.headerService = headerService;
	this.detailService = detailService;
  }

  @Transactional
  @Override
  public String addRentalTransaction(String data) throws Exception {
	ObjectMapper objMapper = new ObjectMapper();
	TransactionRequest request = objMapper.readValue(data.getBytes(), TransactionRequest.class);

	LocalDateTime dateTimeNow = LocalDateTime.now();
	Customer customer = new Customer();
	customer.setProfile(request.getCustomer());

	User user = new User();
	user.setId(request.getUserId());
	Optional.ofNullable(userService.getById(user.getId()))
	    .orElseThrow(() -> new IllegalAccessException("Invalid user!"));

	RentalHeader header = new RentalHeader();
	String receiptNumber = "TRX" + System.currentTimeMillis();
	header.setReceipt(receiptNumber);
	header.setCustomer(customer);
	header.setUser(user);

	List<TransactionDetailRequest> detailRequests = request.getItems();
	if (detailRequests.isEmpty()) {
	  throw new IllegalArgumentException("Detail transaction is empty!");
	}

	List<RentalDetail> detailList = new ArrayList<>();
	detailRequests.forEach(req -> {
	  Inventory inventory = new Inventory();
	  inventory.setId(req.getItemId());

	  RentalDetail detail = new RentalDetail();
	  detail.setInventory(inventory);
	  detail.setRentalDate(dateTimeNow);
	  detail.setReturnDate(dateTimeNow.plusDays(req.getRentalDays()));
	  detailList.add(detail);
	});

	Profile profile = header.getCustomer().getProfile();
	try {
	  Customer validatedCustomer = customerService.getCustomerByEmail(profile.getEmail());
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
