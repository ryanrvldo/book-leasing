package com.lawencon.bookleasing.dao.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.bookleasing.dao.PublisherDao;
import com.lawencon.bookleasing.entity.Publisher;
import com.lawencon.bookleasing.repository.PublisherRepository;

/**
 * @author Rian Rivaldo
 */
@Repository("publisher-jpa")
public class PublisherDaoJpaImpl implements PublisherDao {

  private final PublisherRepository repository;

  @Autowired
  public PublisherDaoJpaImpl(PublisherRepository repository) {
	this.repository = repository;
  }

  @Override
  public Publisher findById(Long id) throws Exception {
	return repository.findById(id).get();
  }

  @Override
  public void insert(Publisher data) throws Exception {
	repository.save(data);
  }

  @Override
  public void update(Publisher data) throws Exception {
	repository.save(data);
  }

  @Override
  public void delete(Publisher data) throws Exception {
	repository.delete(data);
  }

  @Override
  public void deleteById(Long id) throws Exception {
	repository.deleteById(id);
  }

  @Override
  public List<Publisher> findAll() throws Exception {
	return repository.findAll();
  }

  @Override
  public Publisher findByCode(String code) throws Exception {
	return repository.findByCode(code);
  }

}
