package com.lawencon.bookleasing.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.bookleasing.dao.UserDao;
import com.lawencon.bookleasing.entity.Role;
import com.lawencon.bookleasing.entity.User;
import com.lawencon.bookleasing.error.DataAlreadyExistsException;
import com.lawencon.bookleasing.error.DataIsNotExistsException;
import com.lawencon.bookleasing.service.ProfileService;
import com.lawencon.bookleasing.service.RoleService;
import com.lawencon.bookleasing.service.UserService;

/**
 * @author Rian Rivaldo
 */
@Service
public class UserServiceImpl implements UserService {

  private final RoleService roleService;
  private final ProfileService profileService;
  private final UserDao dao;

  @Autowired
  private BCryptPasswordEncoder bCryptEncoder;

  @Autowired
  public UserServiceImpl(RoleService roleService, ProfileService profileService,
      @Qualifier(value = "user-hql") UserDao dao) {
	this.roleService = roleService;
	this.profileService = profileService;
	this.dao = dao;
  }

  @Transactional
  @Override
  public void create(User data) throws Exception {
	validateNewUsername(data.getUsername());

	this.profileService.create(data.getProfile());

	Role role = roleService.getRoleByCode(data.getRole().getCode());
	data.setRole(role);

	String hash = bCryptEncoder.encode(data.getPassword());
	data.setPassword(hash);
	if (data.getIsActive() == null) {
	  data.setIsActive(true);
	}
	this.dao.insert(data);
  }

  @Override
  public void validateNewUsername(String username) throws Exception {
	try {
	  User user = this.getByUsername(username);
	  if (user != null) {
		throw new DataAlreadyExistsException(user.getUsername());
	  }
	} catch (DataIsNotExistsException e) {
	}
  }

  @Override
  public User getByUsername(String username) throws Exception {
	User validatedUser = validateGet(() -> dao.findByUsername(username));
	return Optional.ofNullable(validatedUser)
	    .orElseThrow(() -> new DataIsNotExistsException(username));
  }

  @Override
  public User getById(Long id) throws Exception {
	User validatedUser = validateGet(() -> this.dao.findById(id));
	return Optional.ofNullable(validatedUser)
	    .orElseThrow(() -> new DataIsNotExistsException(id));
  }

  @Override
  public void update(User data) throws Exception {
	this.dao.update(data);
  }

  @Override
  public void delete(User data) throws Exception {
	this.dao.delete(data);
  }

  @Transactional
  @Override
  public void deleteById(Long id) throws Exception {
	User user = this.getById(id);
	this.dao.deleteById(id);
	this.profileService.delete(user.getProfile());
  }

  @Override
  public List<User> getAll() throws Exception {
	return this.dao.findAll();
  }

}
