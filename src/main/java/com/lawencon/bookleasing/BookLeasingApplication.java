package com.lawencon.bookleasing;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lawencon.bookleasing.view.MainView;

/**
 * @author Rian Rivaldo Rumapea
 */
public class BookLeasingApplication {

  public static void main(String[] args) {
    ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("main.xml");
    MainView mainView = context.getBean("mainView", MainView.class);
    mainView.show(() -> {
      System.out.println("\nThank you. See you again!");
      context.close();
      System.exit(0);
    });
  }

}
