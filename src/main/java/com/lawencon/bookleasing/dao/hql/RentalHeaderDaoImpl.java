package com.lawencon.bookleasing.dao.hql;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.bookleasing.dao.RentalHeaderDao;
import com.lawencon.bookleasing.entity.RentalHeader;

/**
 * @author Rian Rivaldo
 */
@Repository("rental-hdr-hql")
public class RentalHeaderDaoImpl extends BaseDaoImpl<RentalHeader> implements RentalHeaderDao {

  public RentalHeaderDaoImpl() {
	super(RentalHeader.class);
  }

  @Override
  public RentalHeader findById(Long id) throws Exception {
	String hql = buildQueryOf(
	    "FROM RentalHeader ",
	    "WHERE id = ?1 ");
	return getSingleResultFromEntity(hql, id);
  }

  @Override
  public RentalHeader findByReceiptNumber(String receiptNumber) throws Exception {
	String hql = buildQueryOf(
	    "FROM RentalHeader ",
	    "WHERE receipt = ?1 ");
	return getSingleResultFromEntity(hql, receiptNumber);
  }

  @Override
  public void insert(RentalHeader data) throws Exception {
	insertData(data);
  }

  @Override
  public void update(RentalHeader data) throws Exception {
	mergeUpdateData(data);
  }

  @Override
  public void deleteById(Long id) throws Exception {
	String hql = buildQueryOf(
	    "DELETE FROM RentalHeader ",
	    "WHERE id = ?1 ");
	deleteEntityById(hql, id);
  }

  @Override
  public void delete(RentalHeader data) throws Exception {
	deleteData(data);
  }

  @Override
  public List<RentalHeader> findAll() throws Exception {
	return getAllData();
  }

}
