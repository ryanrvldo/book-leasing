package com.lawencon.bookleasing.dao.hql;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.bookleasing.dao.PublisherDao;
import com.lawencon.bookleasing.entity.Publisher;

/**
 * @author Rian Rivaldo
 */
@Repository("publisher-hql")
public class PublisherDaoImpl extends BaseDaoImpl<Publisher> implements PublisherDao {

  public PublisherDaoImpl() {
	super(Publisher.class);
  }

  @Override
  public Publisher findById(Long id) throws Exception {
	String hql = buildQueryOf(
	    "FROM Publisher ",
	    "WHERE id = ?1 ");
	return getSingleResultById(hql, id);
  }

  @Override
  public Publisher findByCode(String code) throws Exception {
	String hql = buildQueryOf(
	    "FROM Publisher ",
	    "WHERE lower(code) = lower(?1) ");
	return getSingleResultFromEntity(hql, code);
  }

  @Override
  public void insert(Publisher data) throws Exception {
	insertData(data);
  }

  @Override
  public void update(Publisher data) throws Exception {
	mergeUpdateData(data);
  }

  @Override
  public void deleteById(Long id) throws Exception {
	String hql = buildQueryOf(
	    "DELETE FROM Publisher ",
	    "WHERE id = ?1 ");
	deleteEntityById(hql, id);
  }

  @Override
  public void delete(Publisher data) throws Exception {
	deleteData(data);
  }

  @Override
  public List<Publisher> findAll() throws Exception {
	return getAllData();
  }

}
