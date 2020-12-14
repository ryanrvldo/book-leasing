package com.lawencon.bookleasing.service;

import com.lawencon.bookleasing.model.Profile;
import com.lawencon.bookleasing.model.User;

/**
 * @author Rian Rivaldo Rumapea
 */
public interface AddUserService {

  Profile add(Profile profile) throws Exception;

  User add(User user) throws Exception;

}
