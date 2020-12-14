package com.lawencon.bookleasing.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lawencon.bookleasing.dao.AuthorDao;
import com.lawencon.bookleasing.model.Author;

/**
 * @author Rian Rivaldo Rumapea
 */
public class AuthorDaoImpl implements AuthorDao {

  @Override
  public Author insert(Author data) throws Exception {
    String query = buildSqlQuery(
        "INSERT INTO tb_m_author(first_name, last_name) ",
        "VALUES (?, ?) ",
        "RETURNING id");
    PreparedStatement statement = connection.prepareStatement(query);
    statement.setString(1, data.getFirstName());
    statement.setString(2, data.getLastName());

    ResultSet resultSet = statement.executeQuery();
    if (resultSet.next()) {
      return new Author(resultSet.getLong("id"));
    }
    return null;
  }

  @Override
  public Author get(Author data) throws Exception {
    String query = buildSqlQuery(
        "SELECT * FROM tb_m_author ",
        "WHERE lower(first_name) = lower(?) AND lower(last_name) = lower(?) ");
    PreparedStatement statement = connection.prepareStatement(query);
    statement.setString(1, data.getFirstName());
    statement.setString(2, data.getLastName());

    ResultSet resultSet = statement.executeQuery();
    if (resultSet.next()) {
      return new Author(
          resultSet.getLong("id"),
          resultSet.getString("first_name"),
          resultSet.getString("last_name"));
    }
    return null;
  }

  @Override
  public Author update(Author newData) throws Exception {
    return null;
  }

  @Override
  public Author delete(Author data) throws Exception {
    return null;
  }

}
