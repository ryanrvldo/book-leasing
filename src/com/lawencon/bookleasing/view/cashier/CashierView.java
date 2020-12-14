package com.lawencon.bookleasing.view.cashier;

import com.lawencon.bookleasing.model.User;
import com.lawencon.bookleasing.util.OnViewFinished;
import com.lawencon.bookleasing.view.BaseView;

/**
 * @author Rian Rivaldo Rumapea
 */
public class CashierView extends BaseView {

  private User user;

  public CashierView(User user) {
    this.user = user;
    System.out.printf("%nHello, %s.%n", user.getUsername());
  }

  @Override
  public void show(OnViewFinished onViewFinished) {
    System.out.print(buildMenusOf(
        "\n=============== Cashier ===============\n",
        "1. Rental Book\n",
        "2. Return Book\n",
        "3. Transaction History\n",
        "4. Logout\n",
        "Choose menu: "));
    byte chosenMenu = getInputByte(4);
    switch (chosenMenu) {
      case 1:
        RentalBookView rentalBookView = new RentalBookView(user);
        rentalBookView.show(() -> this.show(onViewFinished));
        break;
      case 2:
        ReturnBookView returnBookView = new ReturnBookView(user);
        returnBookView.show(() -> this.show(onViewFinished));
        break;
      case 3:
        TransactionHistoryView transactionHistoryView = new TransactionHistoryView();
        transactionHistoryView.show(() -> this.show(onViewFinished));
        break;
      case 4:
        user = null;
        break;
      default:
        break;
    }
    onViewFinished.onFinished();
  }

}
