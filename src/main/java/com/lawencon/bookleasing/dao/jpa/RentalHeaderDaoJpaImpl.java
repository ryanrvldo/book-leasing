package com.lawencon.bookleasing.dao.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.bookleasing.dao.RentalHeaderDao;
import com.lawencon.bookleasing.entity.RentalHeader;
import com.lawencon.bookleasing.repository.RentalHeaderRepository;

/**
 * @author Rian Rivaldo
 */
@Repository("rental-hdr-jpa")
public class RentalHeaderDaoJpaImpl implements RentalHeaderDao {

  private final RentalHeaderRepository repository;

  @Autowired
  public RentalHeaderDaoJpaImpl(RentalHeaderRepository repository) {
	this.repository = repository;
  }

  @Override
  public RentalHeader findById(Long id) throws Exception {
	return repository.findById(id).get();
  }

  @Override
  public void insert(RentalHeader data) throws Exception {
	repository.save(data);
  }

  @Override
  public void update(RentalHeader data) throws Exception {
	repository.save(data);
  }

  @Override
  public void delete(RentalHeader data) throws Exception {
	repository.delete(data);
  }

  @Override
  public void deleteById(Long id) throws Exception {
	repository.deleteById(id);
  }

  @Override
  public List<RentalHeader> findAll() throws Exception {
	return repository.findAll();
  }

  @Override
  public RentalHeader findByReceiptNumber(String receiptNumber) throws Exception {
	return repository.findByReceipt(receiptNumber);
  }

}
