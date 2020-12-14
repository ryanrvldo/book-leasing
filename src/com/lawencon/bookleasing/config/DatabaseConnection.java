package com.lawencon.bookleasing.config;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author Rian Rivaldo Rumapea
 */
public class DatabaseConnection {

  private static Connection connection = null;

  public static Connection getConnection() {
    if (connection == null) {
      String url = "jdbc:postgresql://localhost:5432/book_leasing";
      String user = "malaikatmaut";
      String password = "secret";
      try {
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection(url, user, password);
      } catch (Exception e) {
        System.out.println("Database connection failed: " + e.getMessage());
      }
    }
    return connection;
  }

}
