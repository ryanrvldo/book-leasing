package com.lawencon.bookleasing.model;

import com.lawencon.bookleasing.entity.User;

/**
 * @author Rian Rivaldo Rumapea
 */
public class UserSessionCache {

	private User activeUser;

	public User getActiveUser() {
		return this.activeUser;
	}

	public void setActiveUser(User activeUser) {
		this.activeUser = activeUser;
	}

}
