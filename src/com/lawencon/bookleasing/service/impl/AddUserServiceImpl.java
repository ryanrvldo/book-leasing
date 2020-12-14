package com.lawencon.bookleasing.service.impl;

import java.util.Optional;

import com.lawencon.bookleasing.model.Profile;
import com.lawencon.bookleasing.model.User;
import com.lawencon.bookleasing.service.AddUserService;
import com.lawencon.bookleasing.service.ProfileService;
import com.lawencon.bookleasing.service.UserService;

/**
 * @author Rian Rivaldo Rumapea
 */
public class AddUserServiceImpl implements AddUserService {

  private ProfileService profileService = new ProfileServiceImpl();
  private UserService userService = new UserServiceImpl();

  @Override
  public Profile add(Profile profile) throws Exception {
    if (profile == null) {
      throw new NullPointerException();
    }
    return Optional.ofNullable(profileService.add(profile))
        .orElseThrow(() -> new Exception("Failed to add profile to database."));
  }

  @Override
  public User add(User user) throws Exception {
    if (user == null || user.getRole().getId() == null) {
      throw new NullPointerException();
    }

    return Optional.ofNullable(userService.add(user))
        .orElseThrow(() -> new Exception("Failed to add user to database."));
  }

}
