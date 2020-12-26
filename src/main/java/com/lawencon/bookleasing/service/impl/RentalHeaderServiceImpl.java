package com.lawencon.bookleasing.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lawencon.bookleasing.dao.RentalHeaderDao;
import com.lawencon.bookleasing.entity.RentalHeader;
import com.lawencon.bookleasing.service.RentalHeaderService;

/**
 * @author Rian Rivaldo Rumapea
 */
@Service
public class RentalHeaderServiceImpl implements RentalHeaderService {

  private final RentalHeaderDao dao;

  @Autowired
  public RentalHeaderServiceImpl(@Qualifier("rental-hdr-hql") RentalHeaderDao dao) {
	this.dao = dao;
  }

  @Override
  public void create(RentalHeader data) throws Exception {
	this.dao.insert(data);
  }

  @Override
  public RentalHeader getRentalHeaderByReceiptNumber(String receiptNumber) throws Exception {
	return validateGet(() -> dao.findByReceiptNumber(receiptNumber));
  }

  @Override
  public void update(RentalHeader data) throws Exception {
	this.dao.update(data);
  }

  @Override
  public void delete(RentalHeader data) throws Exception {
	this.dao.delete(data);
  }

  @Override
  public List<RentalHeader> getAll() throws Exception {
	return this.dao.findAll();
  }

}
