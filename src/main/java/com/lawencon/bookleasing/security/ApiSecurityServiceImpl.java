package com.lawencon.bookleasing.security;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lawencon.bookleasing.entity.User;
import com.lawencon.bookleasing.service.UserService;

/**
 * @author Rian Rivaldo
 */
@Service
public class ApiSecurityServiceImpl implements UserDetailsService {

  private final UserService userService;

  @Autowired
  public ApiSecurityServiceImpl(UserService userService) {
	this.userService = userService;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	try {
	  User userFromDb = userService.getByUsername(username);
	  if (userFromDb != null) {
		return new org.springframework.security.core.userdetails.User(userFromDb.getUsername(),
		    userFromDb.getPassword(),
		    Collections.emptyList());
	  }
	} catch (Exception e) {
	  e.printStackTrace();
	}
	return null;
  }

}
