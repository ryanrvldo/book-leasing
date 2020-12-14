package com.lawencon.bookleasing.view;

import com.lawencon.bookleasing.constants.Roles;
import com.lawencon.bookleasing.model.Role;
import com.lawencon.bookleasing.model.User;
import com.lawencon.bookleasing.service.UserService;
import com.lawencon.bookleasing.service.impl.UserServiceImpl;
import com.lawencon.bookleasing.util.OnLoginSuccessfully;
import com.lawencon.bookleasing.util.OnViewFinished;
import com.lawencon.bookleasing.view.admin.AdminView;
import com.lawencon.bookleasing.view.cashier.CashierView;

/**
 * @author Rian Rivaldo Rumapea
 */
public class MainView extends BaseView {

  private UserService userService = new UserServiceImpl();

  @Override
  public void show(OnViewFinished onViewFinished) {
    System.out.print(buildMenusOf(
        "\n============ Lawencon Book Leasing ============\n",
        "1. Login\n",
        "2. Exit\n",
        "Choose menu: "));
    byte chosenMenu = getInputByte(2);

    if (chosenMenu < 0) {
      show(onViewFinished);
    } else if (chosenMenu == 1) {
      login(onViewFinished, (user) -> {
        Role userRole = user.getRole();
        if (userRole.getCode().equalsIgnoreCase(Roles.ADMINISTRATOR.getCode())) {
          AdminView adminView = new AdminView(user);
          adminView.show(() -> this.show(onViewFinished));
        } else if (userRole.getCode().equals(Roles.CASHIER.getCode())) {
          CashierView cashierView = new CashierView(user);
          cashierView.show(() -> this.show(onViewFinished));
        }
      });

    }
    onViewFinished.onFinished();
  }

  private void login(OnViewFinished onViewFinished, OnLoginSuccessfully onLoginSuccessfully) {
    System.out.print("Input username: ");
    String username = getInputString();
    System.out.print("Input password: ");
    String password = getInputString();
    User paramUser = new User();
    paramUser.setUsername(username);
    paramUser.setPassword(password);
    try {
      User resultUser = userService.get(paramUser);
      onLoginSuccessfully.onSuccess(resultUser);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      show(onViewFinished);
    }
  }

}
