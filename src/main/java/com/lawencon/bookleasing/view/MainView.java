package com.lawencon.bookleasing.view;

import com.lawencon.bookleasing.constants.Roles;
import com.lawencon.bookleasing.entity.User;
import com.lawencon.bookleasing.service.UserService;
import com.lawencon.bookleasing.util.OnViewFinished;
import com.lawencon.bookleasing.view.admin.AdminView;
import com.lawencon.bookleasing.view.cashier.CashierView;

import java.util.function.Consumer;

/**
 * @author Rian Rivaldo Rumapea
 */
public class MainView extends BaseView {

	private final UserService userService;
	private final AdminView adminView;
	private final CashierView cashierView;

	public MainView(UserService userService, AdminView adminView, CashierView cashierView) {
		this.userService = userService;
		this.adminView = adminView;
		this.cashierView = cashierView;
	}

	@Override
	public void show(OnViewFinished onViewFinished) {
		System.out.print(this.buildMenusOf(
				"\n============ Lawencon Book Leasing ============\n",
				"1. Login\n",
				"2. Exit\n",
				"Choose menu: "));
		Byte chosenMenu = this.readFromInput(Byte.class);

		if (chosenMenu == null) {
			this.show(onViewFinished);
		} else if (chosenMenu < 1) {
			this.show(onViewFinished);
		} else if (chosenMenu == 1) {
			this.login(onViewFinished, (user) -> {
				String roleCode = user.getRole().getCode();
				if (roleCode.equalsIgnoreCase(Roles.ADMINISTRATOR.getCode())) {
					this.adminView.show(() -> this.show(onViewFinished));
				} else if (roleCode.equals(Roles.CASHIER.getCode())) {
					this.cashierView.show(() -> this.show(onViewFinished));
				}
			});
		} else {
			onViewFinished.onFinished();
		}
	}

	private void login(OnViewFinished onViewFinished, Consumer<User> userConsumer) {
		System.out.print("Input username: ");
		String username = this.readFromInput(String.class);
		System.out.print("Input password: ");
		String password = this.readFromInput(String.class);
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		try {
			User validatedUser = this.userService.validateUserLogin(user);
			userConsumer.accept(validatedUser);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			this.show(onViewFinished);
		}
	}

}
