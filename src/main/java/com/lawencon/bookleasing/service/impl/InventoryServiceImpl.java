package com.lawencon.bookleasing.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.bookleasing.dao.InventoryDao;
import com.lawencon.bookleasing.entity.Inventory;
import com.lawencon.bookleasing.entity.InventoryStatus;
import com.lawencon.bookleasing.service.InventoryService;
import com.lawencon.bookleasing.service.InventoryStatusService;

/**
 * @author Rian Rivaldo Rumapea
 */
@Service
public class InventoryServiceImpl implements InventoryService {

  private final InventoryDao dao;
  private final InventoryStatusService statusService;

  @Autowired
  public InventoryServiceImpl(@Qualifier("inventory-hql") InventoryDao dao, InventoryStatusService statusService) {
	this.dao = dao;
	this.statusService = statusService;
  }

  @Override
  public List<Inventory> getInventoryList() throws Exception {
	return Collections.unmodifiableList(dao.findAll());
  }

  @Override
  public void create(Inventory data) throws Exception {
	this.dao.insert(data);
  }

  @Override
  public Inventory getInventoryById(Long id) throws Exception {
	return Optional.ofNullable(validateGet(() -> dao.findById(id)))
	    .orElseThrow(() -> new NullPointerException("Invalid item ID."));
  }

  @Override
  public Inventory getInventoryByBookIsbn(String isbn) throws Exception {
	return validateGet(() -> this.dao.findByBookIsbn(isbn));
  }

  @Override
  public void update(Inventory data) throws Exception {
	dao.update(data);
  }

  @Override
  public void delete(Inventory data) throws Exception {
	dao.delete(data);
  }

  @Override
  public List<Inventory> getAll() throws Exception {
	return dao.findAll();
  }

  @Override
  public List<Inventory> getAllInventoriesByStatusId(Long statusId) throws Exception {
	List<Inventory> resultList = this.getAll()
	    .stream()
	    .filter(distinctByKey(i -> i.getBook().getId()))
	    .filter(i -> i.getStatus().getId().equals(statusId))
	    .collect(Collectors.toUnmodifiableList());
	if (resultList.isEmpty()) throw new NoResultException("Inventory list is empty.");
	return resultList;
  }

  @Override
  public List<InventoryStatus> getAllInventoryStatus() throws Exception {
	List<InventoryStatus> statusList = statusService.getAll();
	if (statusList.isEmpty()) {
	  throw new NoResultException("Inventory status list is empty.");
	}
	return statusList;
  }

  @Transactional
  @Override
  public void createInventoryStatus(InventoryStatus inventoryStatus) throws Exception {
	statusService.create(inventoryStatus);
  }

}
