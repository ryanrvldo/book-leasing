package com.lawencon.bookleasing.util;

import com.lawencon.bookleasing.model.User;

/**
 * @author Rian Rivaldo Rumapea
 */
@FunctionalInterface
public interface OnLoginSuccessfully {

	void onSuccess(User user);

}
