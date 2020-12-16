package com.lawencon.bookleasing.repository.impl;

import com.lawencon.bookleasing.dao.UserDao;
import com.lawencon.bookleasing.entity.User;
import com.lawencon.bookleasing.repository.UserRepository;

import java.util.Optional;

/**
 * @author Rian Rivaldo Rumapea
 */
public class UserRepositoryImpl implements UserRepository {

	private final UserDao userDao;

	public UserRepositoryImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public User get(User user) throws Exception {
		if (user.getUsername() == null || user.getPassword() == null) {
			throw new IllegalArgumentException("Username or password must be not null!");
		}

		return Optional.ofNullable(this.userDao.get(user))
				.orElseThrow(() -> new IllegalArgumentException(
						"Invalid username or password! Make sure you have a valid username and password."));
	}

	@Override
	public User add(User user) throws Exception {
		return Optional.ofNullable(this.userDao.insert(user))
				.orElseThrow();
	}

}
