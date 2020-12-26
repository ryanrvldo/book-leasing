package com.lawencon.bookleasing.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lawencon.bookleasing.dao.RoleDao;
import com.lawencon.bookleasing.entity.Role;
import com.lawencon.bookleasing.error.DataIsNotExistsException;
import com.lawencon.bookleasing.service.RoleService;

/**
 * @author Rian Rivaldo Rumapea
 */
@Service
public class RoleServiceImpl implements RoleService {

  private final RoleDao dao;

  @Autowired
  public RoleServiceImpl(@Qualifier("role-jpa") RoleDao dao) {
	this.dao = dao;
  }

  @Override
  public Role getRoleByCode(String code) throws Exception {
	return Optional.ofNullable(validateGet(() -> this.dao.findByCode(code)))
	    .orElseThrow(() -> new DataIsNotExistsException(code));
  }

  @Transactional
  @Override
  public void create(Role data) throws Exception {
	this.dao.insert(data);
  }

  @Override
  public void update(Role data) throws Exception {
	this.dao.update(data);
  }

  @Override
  public void delete(Role data) throws Exception {
	this.dao.delete(data);
  }

  @Override
  public List<Role> getAll() throws Exception {
	return this.dao.findAll();
  }

}
