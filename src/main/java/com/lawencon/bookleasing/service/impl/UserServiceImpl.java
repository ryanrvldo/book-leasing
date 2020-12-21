package com.lawencon.bookleasing.service.impl;

import com.lawencon.bookleasing.entity.Profile;
import com.lawencon.bookleasing.entity.Role;
import com.lawencon.bookleasing.entity.User;
import com.lawencon.bookleasing.repository.ProfileRepository;
import com.lawencon.bookleasing.repository.RoleRepository;
import com.lawencon.bookleasing.repository.UserRepository;
import com.lawencon.bookleasing.service.UserService;
import com.lawencon.bookleasing.util.UserSessionCache;
import org.springframework.transaction.support.TransactionTemplate;

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
	private final TransactionTemplate transactionTemplate;

	public UserServiceImpl(RoleRepository roleRepository, ProfileRepository profileRepository, UserRepository userRepository,
	                       UserSessionCache userSessionCache, TransactionTemplate transactionTemplate) {
		this.roleRepository = roleRepository;
		this.profileRepository = profileRepository;
		this.userRepository = userRepository;
		this.userSessionCache = userSessionCache;
		this.transactionTemplate = transactionTemplate;
	}

	@Override
	public User validateUserLogin(User user) throws Exception {
		if (user.getUsername() == null || user.getPassword() == null) {
			throw new NullPointerException("Your username and password must be not empty. Please try again.");
		}
		User validatedUser = transactionTemplate.execute(status -> {
			try {
				return this.userRepository.get(user);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		});
		Optional.ofNullable(validatedUser)
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
	public void addNewUser(User user) throws Exception {
		Profile userProfile = user.getProfile();
		if (userProfile.getFirstName() == null || userProfile.getEmail() == null) {
			throw new NullPointerException("User first name and email cannot be empty.");
		} else if (user.getRole().getCode() == null) {
			throw new NullPointerException("A role must be selected.");
		}

		this.transactionTemplate.executeWithoutResult(val -> {
			try {
				profileRepository.add(userProfile);
				Optional.ofNullable(userProfile.getId())
						.ifPresent(userProfile::setId);
				Role userRole = Optional.ofNullable(roleRepository.get(user.getRole()))
						.orElseThrow(() -> new IllegalArgumentException("Invalid role."));
				user.setRole(userRole);

				userRepository.add(user);
				Optional.ofNullable(user.getId())
						.orElseThrow(() -> new Exception("Failed to add new User"));
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			}
		});
	}

}
