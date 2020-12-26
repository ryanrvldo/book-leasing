package com.lawencon.bookleasing.dao.hql;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.bookleasing.dao.ProfileDao;
import com.lawencon.bookleasing.entity.Profile;

/**
 * @author Rian Rivaldo
 */
@Repository("profile-hql")
public class ProfileDaoImpl extends BaseDaoImpl<Profile> implements ProfileDao {

  public ProfileDaoImpl() {
	super(Profile.class);
  }

  @Override
  public Profile findById(Long id) throws Exception {
	String hql = buildQueryOf(
	    "FROM Profile ",
	    "WHERE id = ?1 ");
	return getSingleResultFromEntity(hql, id);
  }

  @Override
  public Profile findByEmailAndPhone(String email, String phone) throws Exception {
	String hql = buildQueryOf(
	    "FROM Profile ",
	    "WHERE lower(email) = ?1 AND lower(phone) = ?2");
	return getSingleResultFromEntity(hql, email, phone);
  }

  @Override
  public void deleteById(Long id) throws Exception {
	String hql = buildQueryOf(
	    "DELETE FROM Profile ",
	    "WHERE id = ?1 ");
	deleteEntityById(hql, id);
  }

  @Override
  public void insert(Profile data) throws Exception {
	insertData(data);
  }

  @Override
  public void update(Profile data) throws Exception {
	mergeUpdateData(data);
  }

  @Override
  public void delete(Profile data) throws Exception {
	deleteData(data);
  }

  @Override
  public List<Profile> findAll() throws Exception {
	return getAllData();
  }

}
