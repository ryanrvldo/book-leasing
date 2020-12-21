package com.lawencon.bookleasing.repository.impl;

import com.lawencon.bookleasing.dao.UserDao;
import com.lawencon.bookleasing.entity.User;
import com.lawencon.bookleasing.repository.UserRepository;

/**
 * @author Rian Rivaldo Rumapea
 */
public class UserRepositoryImpl implements UserRepository {

	private final UserDao userDao;

	public UserRepositoryImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void add(User user) throws Exception {
		this.userDao.insert(user);
	}

	@Override
	public User get(User user) throws Exception {
		return this.userDao.get(user);
	}

}
