package com.lawencon.bookleasing.view.cashier;

import com.lawencon.bookleasing.service.UserService;
import com.lawencon.bookleasing.util.OnViewFinished;
import com.lawencon.bookleasing.view.BaseView;

/**
 * @author Rian Rivaldo Rumapea
 */
public class CashierView extends BaseView {

	private final RentalBookView rentalBookView;
	private final ReturnBookView returnBookView;
	private final TransactionHistoryView transactionHistoryView;
	private final UserService userService;

	public CashierView(RentalBookView rentalBookView, ReturnBookView returnBookView, TransactionHistoryView transactionHistoryView,
	                   UserService userService) {
		this.rentalBookView = rentalBookView;
		this.returnBookView = returnBookView;
		this.transactionHistoryView = transactionHistoryView;
		this.userService = userService;
	}

	@Override
	public void show(OnViewFinished onViewFinished) {
		System.out.print(this.buildMenusOf(
				"\n=============== Cashier ===============\n",
				"1. Rental Book\n",
				"2. Return Book\n",
				"3. Transaction History\n",
				"4. Logout\n",
				"Choose menu: "));
		Byte chosenMenu = this.readFromInput(Byte.class);
		if (chosenMenu == null) {
			onViewFinished.onFinished();
			return;
		}

		OnViewFinished viewCallback = () -> this.show(onViewFinished);
		switch (chosenMenu) {
			case 1:
				this.rentalBookView.show(viewCallback);
				break;
			case 2:
				this.returnBookView.show(viewCallback);
				break;
			case 3:
				this.transactionHistoryView.show(viewCallback);
				break;
			case 4:
				this.logout();
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
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

}
