package com.lawencon.bookleasing.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lawencon.bookleasing.dao.PublisherDao;
import com.lawencon.bookleasing.model.Publisher;

/**
 * @author Rian Rivaldo Rumapea
 */
public class PublisherDaoImpl implements PublisherDao {

  @Override
  public Publisher insert(Publisher data) throws Exception {
    String query = buildSqlQuery(
        "INSERT INTO tb_m_publisher(publisher_name, code, city) ",
        "VALUES (?, ?, ?) ",
        "RETURNING id ");
    PreparedStatement statement = connection.prepareStatement(query);
    statement.setString(1, data.getName());
    statement.setString(2, data.getCode());
    statement.setString(3, data.getCity());

    ResultSet resultSet = statement.executeQuery();
    if (resultSet.next()) {
      Publisher resultPublisher = new Publisher();
      resultPublisher.setId(resultSet.getLong("id"));
      return resultPublisher;
    }
    return null;
  }

  @Override
  public Publisher get(Publisher data) throws Exception {
    String query = buildSqlQuery(
        "SELECT id, code ",
        "FROM tb_m_publisher ",
        "WHERE lower(code) = lower(?) ");
    PreparedStatement statement = connection.prepareStatement(query);
    statement.setString(1, data.getCode());

    ResultSet resultSet = statement.executeQuery();
    if (resultSet.next()) {
      Publisher resultPublisher = new Publisher();
      resultPublisher.setId(resultSet.getLong("id"));
      resultPublisher.setCode(resultSet.getString("code"));
      return resultPublisher;
    }
    return null;
  }

  @Override
  public Publisher update(Publisher newData) throws Exception {
    return null;
  }

  @Override
  public Publisher delete(Publisher data) throws Exception {
    return null;
  }

}
