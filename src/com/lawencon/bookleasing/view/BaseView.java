package com.lawencon.bookleasing.view;

import java.util.Scanner;

import com.lawencon.bookleasing.util.OnViewFinished;

/**
 * @author Rian Rivaldo Rumapea
 */
public abstract class BaseView {

  private Scanner scan;

  public BaseView() {
    scan = new Scanner(System.in);
  }

  public abstract void show(OnViewFinished onViewFinished);

  protected String buildMenusOf(String... menus) {
    StringBuilder builder = new StringBuilder();
    for (String menu : menus) {
      builder.append(menu);
    }
    return builder.toString();
  }

  protected byte getInputByte(int maxInput) {
    try {
      byte chosenMenu = Byte.parseByte(scan.nextLine());
      if (chosenMenu < 1 || chosenMenu > maxInput) {
        throw new IllegalArgumentException();
      }
      return chosenMenu;
    } catch (Exception ex) {
      showInvalidInputMessage();
      return -1;
    }
  }

  protected Short getInputShort() {
    try {
      Short result = Short.valueOf(getInputString());
      return result;
    } catch (Exception e) {
      showInvalidInputMessage();
      return -1;
    }
  }

  protected int getInputInteger() {
    try {
      int result = Integer.parseInt(scan.nextLine());
      return result;
    } catch (Exception e) {
      showInvalidInputMessage();
      return -1;
    }
  }

  protected String getInputString() {
    try {
      String result = scan.nextLine();
      if (result.isBlank() || result.isEmpty()) {
        throw new IllegalArgumentException("Your input must not be null or empty!");
      }
      return result;
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
      return null;
    }
  }

  protected void showInvalidInputMessage() {
    System.out.println("Invalid input. Please try again.");
  }

}
