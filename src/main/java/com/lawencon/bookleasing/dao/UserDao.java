package com.lawencon.bookleasing.dao;

import com.lawencon.bookleasing.entity.User;

/**
 * @author Rian Rivaldo Rumapea
 */
public interface UserDao extends BaseDao<User> {

  User findByUsername(String username) throws Exception;

}
