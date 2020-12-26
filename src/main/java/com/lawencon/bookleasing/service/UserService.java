package com.lawencon.bookleasing.service;

import com.lawencon.bookleasing.entity.User;

/**
 * @author Rian Rivaldo Rumapea
 */
public interface UserService extends BaseService<User> {

  void validateNewUsername(String username) throws Exception;

  User getByUsername(String username) throws Exception;

  User getById(Long id) throws Exception;

  void deleteById(Long id) throws Exception;

}
