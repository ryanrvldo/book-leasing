package com.lawencon.bookleasing.dao.hql;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.bookleasing.dao.RoleDao;
import com.lawencon.bookleasing.entity.Role;

/**
 * @author Rian Rivaldo
 */
@Repository("role-hql")
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {

  public RoleDaoImpl() {
	super(Role.class);
  }

  @Override
  public Role findById(Long id) throws Exception {
	String hql = buildQueryOf(
	    "FROM Role ",
	    "WHERE id = ?1 ");
	return getSingleResultFromEntity(hql, id);
  }

  @Override
  public Role findByCode(String code) throws Exception {
	String hql = buildQueryOf(
	    "FROM Role ",
	    "WHERE lower(code) = lower(?1) ");
	return getSingleResultFromEntity(hql, code);
  }

  @Override
  public void insert(Role data) throws Exception {
	insertData(data);
  }

  @Override
  public void update(Role data) throws Exception {
	mergeUpdateData(data);
  }

  @Override
  public void deleteById(Long id) throws Exception {
	String hql = buildQueryOf(
	    "DELETE FROM Role ",
	    "WHERE id = ?1 ");
	deleteEntityById(hql, id);
  }

  @Override
  public void delete(Role data) throws Exception {
	deleteData(data);
  }

  @Override
  public List<Role> findAll() throws Exception {
	return getAllData();
  }

}
