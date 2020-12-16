package com.lawencon.bookleasing.view;

import java.math.BigDecimal;
import java.util.Scanner;

import com.lawencon.bookleasing.util.OnViewFinished;

/**
 * @author Rian Rivaldo Rumapea
 */
public abstract class BaseView {

  private Scanner scan = new Scanner(System.in);

  public abstract void show(OnViewFinished onViewFinished);

  protected String buildMenusOf(String... menus) {
    StringBuilder builder = new StringBuilder();
    for (String menu : menus) {
      builder.append(menu);
    }
    return builder.toString();
  }

  protected <T> T readFromInput(Class<T> inputClass) {
    try {
      if (inputClass.equals(String.class)) {
        String input = scan.nextLine();
        if (input.isEmpty() || input.isBlank()) {
          throw new IllegalArgumentException("Input must be not empty string!");
        }
        return inputClass.cast(input);
      }

      Number input;
      IllegalArgumentException negativeException = new IllegalArgumentException("Input must be non negative number!");
      if (inputClass.equals(Byte.class)) {
        input = Byte.parseByte(scan.nextLine());
        if (input.byteValue() < 0) throw negativeException;
      } else if (inputClass.equals(Short.class)) {
        input = Short.parseShort(scan.nextLine());
        if (input.shortValue() < 0) throw negativeException;
      } else if (inputClass.equals(Integer.class)) {
        input = Integer.parseInt(scan.nextLine());
        if (input.intValue() < 0) throw negativeException;
      } else if (inputClass.equals(Long.class)) {
        input = Long.parseLong(scan.nextLine());
        if (input.longValue() < 0) throw negativeException;
      } else if (inputClass.equals(Double.class)) {
        input = Double.parseDouble(scan.nextLine());
        if (input.doubleValue() < 0) throw negativeException;
      } else if (inputClass.equals(BigDecimal.class)) {
        input = BigDecimal.valueOf(Double.parseDouble(scan.nextLine()));
        if (input.doubleValue() < 0) throw negativeException;
      } else {
        throw new ClassCastException("Not a valid input class.");
      }
      return inputClass.cast(input);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return null;
    }
  }

  protected void showInvalidInputMessage() {
    System.out.println("Invalid input. Please try again.");
  }

}
