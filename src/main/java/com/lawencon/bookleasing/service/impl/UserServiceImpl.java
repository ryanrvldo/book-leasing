package com.lawencon.bookleasing.service.impl;

import com.lawencon.bookleasing.entity.Profile;
import com.lawencon.bookleasing.entity.Role;
import com.lawencon.bookleasing.entity.User;
import com.lawencon.bookleasing.model.UserSessionCache;
import com.lawencon.bookleasing.repository.ProfileRepository;
import com.lawencon.bookleasing.repository.RoleRepository;
import com.lawencon.bookleasing.repository.UserRepository;
import com.lawencon.bookleasing.service.UserService;

import javax.security.auth.login.FailedLoginException;
import java.util.Optional;

/**
 * @author Rian Rivaldo Rumapea
 */
public class UserServiceImpl implements UserService {

	private final RoleRepository roleRepository;
	private final ProfileRepository profileRepository;
	private final UserRepository userRepository;
	private final UserSessionCache userSessionCache;

	public UserServiceImpl(RoleRepository roleRepository, ProfileRepository profileRepository, UserRepository userRepository,
	                       UserSessionCache userSessionCache) {
		this.roleRepository = roleRepository;
		this.profileRepository = profileRepository;
		this.userRepository = userRepository;
		this.userSessionCache = userSessionCache;
	}

	@Override
	public User validateUserLogin(User user) throws Exception {
		if (user.getUsername() == null || user.getPassword() == null) {
			throw new NullPointerException("Your username and password must be not empty. Please try again.");
		}

		User validatedUser = Optional.ofNullable(this.userRepository.get(user))
				.orElseThrow(() -> new FailedLoginException("Invalid username or password."));
		this.userSessionCache.setActiveUser(validatedUser);
		return validatedUser;
	}

	@Override
	public void removeUserSessionCache() throws Exception {
		if (this.userSessionCache.getActiveUser() == null) {
			throw new Exception("There is no active user in the session.");
		}
		this.userSessionCache.setActiveUser(null);
	}

	@Override
	public User addNewUser(User user) throws Exception {
		Profile userProfile = user.getProfile();
		if (userProfile.getFirstName() == null || userProfile.getEmail() == null) {
			throw new NullPointerException("User first name and email cannot be empty.");
		}
		Optional.ofNullable(profileRepository.add(userProfile))
				.ifPresent(profile -> userProfile.setId(profile.getId()));

		if (user.getRole().getCode() == null) {
			throw new NullPointerException("A role must be selected.");
		}
		Role userRole = Optional.ofNullable(roleRepository.get(user.getRole()))
				.orElseThrow(() -> new IllegalArgumentException("Invalid role."));
		user.setRole(userRole);

		return Optional.ofNullable(userRepository.add(user))
				.orElseThrow(() -> new Exception("Failed to add new User"));
	}

}
