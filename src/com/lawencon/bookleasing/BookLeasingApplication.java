package com.lawencon.bookleasing;

import com.lawencon.bookleasing.view.MainView;

/**
 * @author Rian Rivaldo Rumapea
 */
public class BookLeasingApplication {

  public static void main(String[] args) {
    new MainView().show(() -> {
      System.out.println("\nThankyou. See you again!");
      System.exit(0);
    });
  }

}
