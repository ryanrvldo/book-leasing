package com.lawencon.bookleasing.view.admin;

import com.lawencon.bookleasing.constants.Roles;
import com.lawencon.bookleasing.entity.Profile;
import com.lawencon.bookleasing.entity.Role;
import com.lawencon.bookleasing.entity.User;
import com.lawencon.bookleasing.service.UserService;
import com.lawencon.bookleasing.util.OnViewFinished;
import com.lawencon.bookleasing.view.BaseView;

/**
 * @author Rian Rivaldo Rumapea
 */
public class AddUserView extends BaseView {

	private final UserService service;

	public AddUserView(UserService service) {
		this.service = service;
	}

	@Override
	public void show(OnViewFinished onViewFinished) {
		System.out.print("Input first name: ");
		String firstName = readFromInput(String.class);
		System.out.print("Input last name: ");
		String lastName = readFromInput(String.class);
		System.out.print("Input email: ");
		String email = readFromInput(String.class);
		System.out.print("Input phone: ");
		String phone = readFromInput(String.class);

		Profile profile = new Profile();
		profile.setFirstName(firstName);
		profile.setLastName(lastName);
		profile.setEmail(email);
		profile.setPhone(phone);

		System.out.print("Input username: ");
		String username = readFromInput(String.class);
		System.out.print("Input password: ");
		String password = readFromInput(String.class);

		System.out.print(buildMenusOf(
				"Role user:\n",
				"1. Administrator\n",
				"2. Cashier\n",
				"Choose role: "));
		Byte chosenRole = readFromInput(Byte.class);
		if (chosenRole == null || chosenRole < 1 || chosenRole > 2) {
			onViewFinished.onFinished();
			return;
		}

		Role role = new Role();
		if (chosenRole == 1) {
			role.setCode(Roles.ADMINISTRATOR.getCode());
		} else {
			role.setCode(Roles.CASHIER.getCode());
		}

		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setIsActive(true);
		user.setProfile(profile);
		user.setRole(role);

		try {
			service.addNewUser(user);
			System.out.printf("Success added %s to database!", user.getUsername());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		onViewFinished.onFinished();
	}

}
