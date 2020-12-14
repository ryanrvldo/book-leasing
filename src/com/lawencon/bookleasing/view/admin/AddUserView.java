package com.lawencon.bookleasing.view.admin;

import com.lawencon.bookleasing.model.Profile;
import com.lawencon.bookleasing.model.Role;
import com.lawencon.bookleasing.model.User;
import com.lawencon.bookleasing.service.AddUserService;
import com.lawencon.bookleasing.service.impl.AddUserServiceImpl;
import com.lawencon.bookleasing.util.OnViewFinished;
import com.lawencon.bookleasing.view.BaseView;

/**
 * @author Rian Rivaldo Rumapea
 */
public class AddUserView extends BaseView {

  private AddUserService service = new AddUserServiceImpl();

  @Override
  public void show(OnViewFinished onViewFinished) {
    System.out.print("Input first name: ");
    String firstName = getInputString();
    System.out.print("Input last name: ");
    String lastName = getInputString();
    System.out.print("Input email: ");
    String email = getInputString();
    System.out.print("Input phone: ");
    String phone = getInputString();

    Profile profile = new Profile();
    try {
      profile = service.add(new Profile(firstName, lastName, email, phone));
    } catch (Exception e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
      onViewFinished.onFinished();
    }

    System.out.print("Input username: ");
    String username = getInputString();
    System.out.print("Input password: ");
    String password = getInputString();
    System.out.print(buildMenusOf(
        "Role user:\n",
        "1. Administrator\n",
        "2. Cashier\n",
        "Choose role: "));

    byte chosenRole = getInputByte(2);
    if (chosenRole < 0) {
      onViewFinished.onFinished();
    }

    Role role = new Role();
    role.setId((long) chosenRole);

    User user = new User();
    user.setUsername(username);
    user.setPassword(password);
    user.setIsActive(true);
    user.setProfile(profile);
    user.setRole(role);
    try {
      service.add(user);
      System.out.printf("Success added %s to database!", username);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
    }
    onViewFinished.onFinished();
  }

}
