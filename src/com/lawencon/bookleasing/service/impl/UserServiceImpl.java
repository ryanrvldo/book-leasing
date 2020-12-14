package com.lawencon.bookleasing.service.impl;

import java.util.Optional;

import com.lawencon.bookleasing.dao.UserDao;
import com.lawencon.bookleasing.dao.impl.UserDaoImpl;
import com.lawencon.bookleasing.model.User;
import com.lawencon.bookleasing.service.UserService;

/**
 * @author Rian Rivaldo Rumapea
 */
public class UserServiceImpl implements UserService {

  private UserDao userDao;

  public UserServiceImpl() {
    userDao = new UserDaoImpl();
  }

  @Override
  public User get(User user) throws Exception {
    if (user.getUsername() == null || user.getPassword() == null) {
      throw new IllegalArgumentException("Username or password must be not null!");
    }

    return Optional.ofNullable(userDao.get(user))
        .orElseThrow(() -> new IllegalArgumentException(
            "Invalid username or password! Make sure you have a valid username and password."));
  }

  @Override
  public User add(User user) throws Exception {
    return Optional.ofNullable(userDao.insert(user))
        .orElseThrow();
  }

}
