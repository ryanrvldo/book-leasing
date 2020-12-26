package com.lawencon.bookleasing.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lawencon.bookleasing.dao.ReturnDao;
import com.lawencon.bookleasing.entity.Return;
import com.lawencon.bookleasing.service.ReturnService;

/**
 * @author Rian Rivaldo Rumapea
 */
@Service
public class ReturnServiceImpl implements ReturnService {

  private final ReturnDao dao;

  @Autowired
  public ReturnServiceImpl(@Qualifier("return-jpa") ReturnDao dao) {
	this.dao = dao;
  }

  @Override
  public BigDecimal getTotalRentalCost(String receiptNumber) throws Exception {
	return BigDecimal.valueOf(this.dao.getRentalCost(receiptNumber));
  }

  @Override
  public void create(Return data) throws Exception {
	this.dao.insert(data);
  }

  @Override
  public Return getReturnByHeaderId(Long headerId) throws Exception {
	return this.getReturnByHeaderId(headerId);
  }

  @Override
  public void update(Return data) throws Exception {
	this.dao.update(data);
  }

  @Override
  public void delete(Return data) throws Exception {
	this.dao.delete(data);
  }

  @Override
  public List<Return> getAll() throws Exception {
	return this.dao.findAll();
  }

}
