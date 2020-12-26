package com.lawencon.bookleasing.dao.hql;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.bookleasing.dao.InventoryStatusDao;
import com.lawencon.bookleasing.entity.InventoryStatus;

/**
 * @author Rian Rivaldo
 */
@Repository("inventory-status-hql")
public class InventoryStatusDaoImpl extends BaseDaoImpl<InventoryStatus> implements InventoryStatusDao {

  public InventoryStatusDaoImpl() {
	super(InventoryStatus.class);
  }

  @Override
  public InventoryStatus findById(Long id) throws Exception {
	String hql = buildQueryOf(
	    "FROM InventoryStatus ",
	    "WHERE id = ?1 ");
	return getSingleResultById(hql, id);
  }

  @Override
  public void insert(InventoryStatus data) throws Exception {
	insertData(data);
  }

  @Override
  public void update(InventoryStatus data) throws Exception {
	mergeUpdateData(data);
  }

  @Override
  public void delete(InventoryStatus data) throws Exception {
	deleteData(data);
  }

  @Override
  public void deleteById(Long id) throws Exception {
	String hql = buildQueryOf(
	    "DELETE FROM InventoryStatus ",
	    "WHERE id = ?1 ");
	deleteEntityById(hql, id);
  }

  @Override
  public List<InventoryStatus> findAll() throws Exception {
	return getAllData();
  }

  @Override
  public InventoryStatus findByStatus(String status) throws Exception {
	String hql = buildQueryOf(
	    "FROM InventoryStatus ",
	    "WHERE lower(status) = lower(?1) ");
	return getSingleResultFromEntity(hql, status);
  }

}
