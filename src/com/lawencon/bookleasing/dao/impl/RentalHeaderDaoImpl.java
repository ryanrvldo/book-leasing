package com.lawencon.bookleasing.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lawencon.bookleasing.dao.RentalHeaderDao;
import com.lawencon.bookleasing.model.RentalHeader;

/**
 * @author Rian Rivaldo Rumapea
 */
public class RentalHeaderDaoImpl implements RentalHeaderDao {

  @Override
  public RentalHeader insert(RentalHeader data) throws Exception {
    String query = buildSqlQuery(
        "INSERT INTO tb_r_hdr_rental (receipt, customer_id, user_id) ",
        "VALUES (?, ?, ?) ",
        "RETURNING id");
    PreparedStatement statement = connection.prepareStatement(query);
    statement.setString(1, data.getReceipt());
    statement.setLong(2, data.getCustomer().getId());
    statement.setLong(3, data.getUser().getId());

    ResultSet resultSet = statement.executeQuery();
    if (resultSet.next()) {
      RentalHeader resultHeader = new RentalHeader();
      resultHeader.setId(resultSet.getLong("id"));
      return resultHeader;
    }
    return null;
  }

  @Override
  public RentalHeader get(RentalHeader data) throws Exception {
    String query = buildSqlQuery(
        "SELECT id, receipt ",
        "FROM tb_r_hdr_rental ",
        "WHERE receipt = ? ");
    PreparedStatement statement = connection.prepareStatement(query);
    statement.setString(1, data.getReceipt());

    ResultSet resultSet = statement.executeQuery();
    if (resultSet.next()) {
      RentalHeader resultHeader = new RentalHeader();
      resultHeader.setId(resultSet.getLong("id"));
      resultHeader.setReceipt(resultSet.getString("receipt"));
      return resultHeader;
    }
    return null;
  }

  @Override
  public RentalHeader update(RentalHeader newData) throws Exception {
    return null;
  }

  @Override
  public RentalHeader delete(RentalHeader data) throws Exception {
    return null;
  }

}
