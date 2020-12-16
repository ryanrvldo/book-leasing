package com.lawencon.bookleasing.view.admin;

import com.lawencon.bookleasing.service.UserService;
import com.lawencon.bookleasing.util.OnViewFinished;
import com.lawencon.bookleasing.view.BaseView;

/**
 * @author Rian Rivaldo Rumapea
 */
public class AdminView extends BaseView {

	private final AddBookView addBookView;
	private final DeleteBookView deleteBookView;
	private final AddUserView addUserView;
	private final UserService userService;

	public AdminView(AddBookView addBookView, DeleteBookView deleteBookView, AddUserView addUserView, UserService userService) {
		this.addBookView = addBookView;
		this.deleteBookView = deleteBookView;
		this.addUserView = addUserView;
		this.userService = userService;
	}

	@Override
	public void show(OnViewFinished onViewFinished) {
		System.out.print(this.buildMenusOf(
				"\n=============== Admin ===============\n",
				"1. Add Book\n",
				"2. Delete Book\n",
				"3. Update Book\n",
				"4. View Book\n",
				"5. Transaction\n",
				"6. Add User\n",
				"7. Logout\n",
				"Choose menu: "));
		Byte chosenMenu = this.readFromInput(Byte.class);
		if (chosenMenu == null) {
			onViewFinished.onFinished();
			return;
		}

		OnViewFinished viewCallback = () -> this.show(onViewFinished);
		switch (chosenMenu) {
			case 1:
				this.addBookView.show(viewCallback);
				break;
			case 2:
				this.deleteBookView.show(viewCallback);
			case 3:
			case 4:
			case 5:
				System.out.println("Under Construction");
				break;
			case 6:
				this.addUserView.show(viewCallback);
				break;
			case 7:
				logout();
				break;
			default:
				break;
		}
		onViewFinished.onFinished();
	}

	private void logout() {
		try {
			this.userService.removeUserSessionCache();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
