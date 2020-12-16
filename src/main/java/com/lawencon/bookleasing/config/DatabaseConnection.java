package com.lawencon.bookleasing.config;

import java.sql.Connection;

import javax.sql.DataSource;

/**
 * @author Rian Rivaldo Rumapea
 */
public class DatabaseConnection {

  private final DataSource dataSource;

  public DatabaseConnection(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  public Connection getConnection() {
    try {
      return dataSource.getConnection();
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Database connection failed: " + e.getMessage());
      return null;
    }
  }

}
