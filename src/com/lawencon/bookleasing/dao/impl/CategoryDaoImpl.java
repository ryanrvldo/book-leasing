package com.lawencon.bookleasing.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lawencon.bookleasing.dao.CategoryDao;
import com.lawencon.bookleasing.model.Category;

/**
 * @author Rian Rivaldo Rumapea
 */
public class CategoryDaoImpl implements CategoryDao {

  @Override
  public Category insert(Category data) throws Exception {
    String query = buildSqlQuery(
        "INSERT INTO tb_m_category(category_name) ",
        "VALUES (?) ",
        "RETURNING id");
    PreparedStatement statement = connection.prepareStatement(query);
    statement.setString(1, data.getName());

    ResultSet resultSet = statement.executeQuery();
    if (resultSet.next()) {
      return new Category(resultSet.getLong("id"));
    }
    return null;
  }

  @Override
  public Category get(Category data) throws Exception {
    String query = buildSqlQuery(
        "SELECT * FROM tb_m_category ",
        "WHERE lower(category_name) = lower(?) ");
    PreparedStatement statement = connection.prepareStatement(query);
    statement.setString(1, data.getName());

    ResultSet resultSet = statement.executeQuery();
    if (resultSet.next()) {
      return new Category(resultSet.getLong("id"), resultSet.getString("category_name"));
    }
    return null;
  }

  @Override
  public Category update(Category newData) throws Exception {
    return null;
  }

  @Override
  public Category delete(Category data) throws Exception {
    return null;
  }

}
