package com.lawencon.bookleasing.service;

import com.lawencon.bookleasing.entity.User;

/**
 * @author Rian Rivaldo Rumapea
 */
public interface UserService {

	/**
	 * Validating user login.
	 *
	 * @param user model that contains username and password
	 * @return validated user
	 * @throws Exception throw exception if user don't exist or username and
	 *                   password didn't exists.
	 */
	User validateUserLogin(User user) throws Exception;

	/**
	 * Removing active user from session cache.
	 *
	 * @throws Exception throw exception if there is no active user in session
	 *                   cache.
	 */
	void removeUserSessionCache() throws Exception;

	/**
	 * Add new user to database.
	 *
	 * @param user the user to be added
	 * @return user that have been added
	 * @throws Exception throws if user was not added successfully
	 */
	User addNewUser(User user) throws Exception;

}
