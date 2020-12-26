package com.lawencon.bookleasing.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lawencon.bookleasing.dao.PublisherDao;
import com.lawencon.bookleasing.entity.Publisher;
import com.lawencon.bookleasing.service.PublisherService;

/**
 * @author Rian Rivaldo Rumapea
 */
@Service
public class PublisherServiceImpl implements PublisherService {

  private final PublisherDao dao;

  @Autowired
  public PublisherServiceImpl(@Qualifier("publisher-hql") PublisherDao dao) {
	this.dao = dao;
  }

  @Override
  public void create(Publisher data) throws Exception {
	this.dao.insert(data);
  }

  @Override
  public Publisher getPublisherByCode(String code) throws Exception {
	return validateGet(() -> this.dao.findByCode(code));
  }

  @Override
  public void update(Publisher data) throws Exception {
	this.dao.update(data);
  }

  @Override
  public void delete(Publisher data) throws Exception {
	this.dao.update(data);
  }

  @Override
  public List<Publisher> getAll() throws Exception {
	return this.dao.findAll();
  }

}
