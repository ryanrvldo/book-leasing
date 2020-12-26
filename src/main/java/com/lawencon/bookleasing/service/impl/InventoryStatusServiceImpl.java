package com.lawencon.bookleasing.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lawencon.bookleasing.dao.InventoryStatusDao;
import com.lawencon.bookleasing.entity.InventoryStatus;
import com.lawencon.bookleasing.service.InventoryStatusService;

/**
 * @author Rian Rivaldo
 */
@Service
public class InventoryStatusServiceImpl implements InventoryStatusService {

  private final InventoryStatusDao dao;

  @Autowired
  public InventoryStatusServiceImpl(@Qualifier("inventory-status-jpa") InventoryStatusDao dao) {
	this.dao = dao;
  }

  @Override
  public void create(InventoryStatus data) throws Exception {
	dao.insert(data);
  }

  @Override
  public void update(InventoryStatus data) throws Exception {
	dao.update(data);
  }

  @Override
  public void delete(InventoryStatus data) throws Exception {
	dao.delete(data);
  }

  @Override
  public List<InventoryStatus> getAll() throws Exception {
	return dao.findAll();
  }

  @Override
  public InventoryStatus getByStatus(String status) throws Exception {
	return validateGet(() -> dao.findByStatus(status));
  }

}
