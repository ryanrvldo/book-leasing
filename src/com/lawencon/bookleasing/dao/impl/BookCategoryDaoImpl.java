package com.lawencon.bookleasing.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lawencon.bookleasing.dao.BookCategoryDao;
import com.lawencon.bookleasing.model.Book;
import com.lawencon.bookleasing.model.BookCategory;
import com.lawencon.bookleasing.model.Category;

/**
 * @author Rian Rivaldo Rumapea
 */
public class BookCategoryDaoImpl implements BookCategoryDao {

  @Override
  public BookCategory insert(BookCategory data) throws Exception {
    String query = buildSqlQuery(
        "INSERT INTO tb_m_book_category (book_id, category_id) ",
        "VALUES (?, ?) ",
        "RETURNING category_id");
    PreparedStatement statement = connection.prepareStatement(query);
    statement.setLong(1, data.getBook().getId());
    statement.setLong(2, data.getCategory().getId());

    ResultSet resultSet = statement.executeQuery();
    if (resultSet.next()) {
      return new BookCategory(new Category(resultSet.getLong("category_id")));
    }
    return null;
  }

  @Override
  public BookCategory get(BookCategory data) throws Exception {
    String query = buildSqlQuery(
        "SELECT * FROM tb_m_book_category ",
        "WHERE book_id = ? AND category_id = ?");
    PreparedStatement statement = connection.prepareStatement(query);
    statement.setLong(1, data.getBook().getId());
    statement.setLong(2, data.getCategory().getId());

    ResultSet resultSet = statement.executeQuery();
    if (resultSet.next()) {
      return new BookCategory(
          new Category(resultSet.getLong("category_id")),
          new Book(resultSet.getLong("book_id")));
    }
    return null;
  }

  @Override
  public BookCategory update(BookCategory newData) throws Exception {
    return null;
  }

  @Override
  public BookCategory delete(BookCategory data) throws Exception {
    return null;
  }

}
