package com.lawencon.bookleasing.view.admin;

import com.lawencon.bookleasing.model.User;
import com.lawencon.bookleasing.util.OnViewFinished;
import com.lawencon.bookleasing.view.BaseView;

/**
 * @author Rian Rivaldo Rumapea
 */
public class AdminView extends BaseView {

  public AdminView(User user) {
    System.out.printf("%nHello, %s.%n", user.getUsername());
  }

  @Override
  public void show(OnViewFinished onViewFinished) {
    System.out.print(buildMenusOf(
        "\n=============== Admin ===============\n",
        "1. Add Book\n",
        "2. Delete Book\n",
        "3. Update Book\n",
        "4. View Book\n",
        "5. Transaction\n",
        "6. Add User\n",
        "7. Logout\n",
        "Choose menu: "));
    byte chosenMenu = getInputByte(7);
    switch (chosenMenu) {
      case 1:
        AddBookView addBookView = new AddBookView();
        addBookView.show(() -> this.show(onViewFinished));
        break;
      case 2:
        DeleteBookView deleteBookView = new DeleteBookView();
        deleteBookView.show(() -> this.show(onViewFinished));
      case 3:
      case 4:
      case 5:
        System.out.println("Under Construction");
        break;
      case 6:
        AddUserView addUserView = new AddUserView();
        addUserView.show(() -> this.show(onViewFinished));
      default:
        break;
    }
    onViewFinished.onFinished();
  }

}
