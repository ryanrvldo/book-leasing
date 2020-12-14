package com.lawencon.bookleasing.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lawencon.bookleasing.dao.BookDao;
import com.lawencon.bookleasing.model.Book;

/**
 * @author Rian Rivaldo Rumapea
 */
public class BookDaoImpl implements BookDao {

  @Override
  public Book insert(Book data) throws Exception {
    String query = buildSqlQuery(
        "INSERT INTO tb_m_book (title, isbn, publication_year, publisher_id, language_id, rental_cost, replacement_cost) ",
        "VALUES (?, ?, ?, ?, ?, ?, ?) ",
        "RETURNING id");
    PreparedStatement statement = connection.prepareStatement(query);
    statement.setString(1, data.getTitle());
    statement.setString(2, data.getIsbn());
    statement.setShort(3, data.getPublicationYear());
    statement.setLong(4, data.getPublisher().getId());
    statement.setLong(5, data.getLanguage().getId());
    statement.setBigDecimal(6, data.getRentalCost());
    statement.setBigDecimal(7, data.getReplacementCost());

    ResultSet resultSet = statement.executeQuery();
    if (resultSet.next()) {
      return new Book(resultSet.getLong("id"));
    }
    return null;
  }

  @Override
  public Book get(Book data) throws Exception {
    return null;
  }

  @Override
  public Book update(Book newData) throws Exception {
    return null;
  }

  @Override
  public Book delete(Book data) throws Exception {
    return null;
  }

}
