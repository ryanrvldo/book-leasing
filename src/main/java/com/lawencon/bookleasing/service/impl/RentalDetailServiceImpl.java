package com.lawencon.bookleasing.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lawencon.bookleasing.constants.Constants;
import com.lawencon.bookleasing.dao.RentalDetailDao;
import com.lawencon.bookleasing.entity.Inventory;
import com.lawencon.bookleasing.entity.InventoryStatus;
import com.lawencon.bookleasing.entity.RentalDetail;
import com.lawencon.bookleasing.error.DataIsNotExistsException;
import com.lawencon.bookleasing.service.InventoryService;
import com.lawencon.bookleasing.service.RentalDetailService;

/**
 * @author Rian Rivaldo
 */
@Service
public class RentalDetailServiceImpl implements RentalDetailService {

  private final RentalDetailDao dao;
  private final InventoryService inventoryService;

  @Autowired
  public RentalDetailServiceImpl(@Qualifier("rental-dtl-hql") RentalDetailDao dao, InventoryService inventoryService) {
	this.dao = dao;
	this.inventoryService = inventoryService;
  }

  @Override
  public void create(RentalDetail data) throws Exception {
	Long inventoryId = data.getInventory().getId();
	Inventory inventory = Optional.ofNullable(inventoryService.getInventoryById(inventoryId))
	    .orElseThrow(() -> new DataIsNotExistsException(inventoryId));

	InventoryStatus borrowedStatus = inventoryService.getAllInventoryStatus()
	    .stream()
	    .filter(status -> status.getStatus().equalsIgnoreCase(Constants.BORROWED_STATUS))
	    .findFirst()
	    .get();
	if (inventory.getStatus().getId().equals(borrowedStatus.getId())) {
	  throw new IllegalArgumentException("Book is not available for rental!");
	}
	inventory.setStatus(borrowedStatus);
	inventoryService.update(inventory);

	this.dao.insert(data);
  }

  @Override
  public void update(RentalDetail data) throws Exception {
	this.dao.update(data);
  }

  @Override
  public void delete(RentalDetail data) throws Exception {
	this.dao.delete(data);
  }

  @Override
  public List<RentalDetail> getAll() throws Exception {
	return this.dao.findAll();
  }

  @Override
  public List<RentalDetail> findAllByReceiptNumber(String receiptNumber) throws Exception {
	return dao.findAllByReceiptNumber(receiptNumber);
  }

}
