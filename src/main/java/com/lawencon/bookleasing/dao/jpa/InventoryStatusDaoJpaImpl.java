package com.lawencon.bookleasing.dao.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.bookleasing.dao.InventoryStatusDao;
import com.lawencon.bookleasing.entity.InventoryStatus;
import com.lawencon.bookleasing.repository.InventoryStatusRepository;

/**
 * @author Rian Rivaldo
 */
@Repository("inventory-status-jpa")
public class InventoryStatusDaoJpaImpl implements InventoryStatusDao {

  private final InventoryStatusRepository repository;

  @Autowired
  public InventoryStatusDaoJpaImpl(InventoryStatusRepository repository) {
	this.repository = repository;
  }

  @Override
  public InventoryStatus findById(Long id) throws Exception {
	return repository.findById(id).get();
  }

  @Override
  public void insert(InventoryStatus data) throws Exception {
	repository.save(data);
  }

  @Override
  public void update(InventoryStatus data) throws Exception {
	repository.save(data);
  }

  @Override
  public void delete(InventoryStatus data) throws Exception {
	repository.delete(data);
  }

  @Override
  public void deleteById(Long id) throws Exception {
	repository.deleteById(id);
  }

  @Override
  public List<InventoryStatus> findAll() throws Exception {
	return repository.findAll();
  }

  @Override
  public InventoryStatus findByStatus(String status) throws Exception {
	return repository.findByStatus(status);
  }

}
