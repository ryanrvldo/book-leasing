package com.lawencon.bookleasing.util;

import com.lawencon.bookleasing.entity.User;
import org.springframework.stereotype.Component;

/**
 * @author Rian Rivaldo Rumapea
 */
@Component
public class UserSessionCache {

	private User activeUser;

	public User getActiveUser() {
		return this.activeUser;
	}

	public void setActiveUser(User activeUser) {
		this.activeUser = activeUser;
	}

}
