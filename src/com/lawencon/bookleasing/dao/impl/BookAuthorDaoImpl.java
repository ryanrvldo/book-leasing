package com.lawencon.bookleasing.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lawencon.bookleasing.dao.BookAuthorDao;
import com.lawencon.bookleasing.model.Author;
import com.lawencon.bookleasing.model.Book;
import com.lawencon.bookleasing.model.BookAuthor;

/**
 * @author Rian Rivaldo Rumapea
 */
public class BookAuthorDaoImpl implements BookAuthorDao {

  @Override
  public BookAuthor insert(BookAuthor data) throws Exception {
    String query = buildSqlQuery(
        "INSERT INTO tb_m_book_author (author_id, book_id) ",
        "VALUES (?, ?) ",
        "RETURNING author_id ");
    PreparedStatement statement = connection.prepareStatement(query);
    statement.setLong(1, data.getAuthor().getId());
    statement.setLong(2, data.getBook().getId());

    ResultSet resultSet = statement.executeQuery();
    if (resultSet.next()) {
      return new BookAuthor(new Author(resultSet.getLong("author_id")));
    }
    return null;
  }

  @Override
  public BookAuthor get(BookAuthor data) throws Exception {
    String query = buildSqlQuery(
        "SELECT * FROM tb_m_book_author ",
        "WHERE author_id = ? AND book_id = ? ");
    PreparedStatement statement = connection.prepareStatement(query);
    statement.setLong(1, data.getAuthor().getId());
    statement.setLong(2, data.getBook().getId());

    ResultSet resultSet = statement.executeQuery();
    if (resultSet.next()) {
      return new BookAuthor(
          new Author(resultSet.getLong("author_id")),
          new Book(resultSet.getLong("book_id")));
    }
    return null;
  }

  @Override
  public BookAuthor update(BookAuthor newData) throws Exception {
    return null;
  }

  @Override
  public BookAuthor delete(BookAuthor data) throws Exception {
    return null;
  }

}
