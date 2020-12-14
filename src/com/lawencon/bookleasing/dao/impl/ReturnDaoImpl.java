package com.lawencon.bookleasing.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lawencon.bookleasing.dao.ReturnDao;
import com.lawencon.bookleasing.model.RentalHeader;
import com.lawencon.bookleasing.model.Return;

/**
 * @author Rian Rivaldo Rumapea
 */
public class ReturnDaoImpl implements ReturnDao {

  @Override
  public Double getRentalCost(String receiptNumber) throws Exception {
    String query = buildSqlQuery(
        "SELECT sum(dtl.total_price) AS rental_cost ",
        "FROM tb_r_return AS r ",
        "RIGHT JOIN tb_r_hdr_rental AS hdr ON hdr.id = r.rental_hdr_id ",
        "RIGHT JOIN tb_r_dtl_rental AS dtl ON dtl.rental_hdr_id = hdr.id ",
        "GROUP BY hdr.id, hdr.receipt ",
        "HAVING hdr.receipt = ? ;");
    PreparedStatement statement = connection.prepareStatement(query);
    statement.setString(1, receiptNumber);

    ResultSet resultSet = statement.executeQuery();
    if (resultSet.next()) {
      return resultSet.getDouble("rental_cost");
    }
    return null;
  }

  @Override
  public Return insert(Return data) throws Exception {
    String query = buildSqlQuery(
        "INSERT INTO tb_r_return (rental_hdr_id, returned_at, user_id, total_price) ",
        "VALUES (?, ?, ?, ?) ",
        "RETURNING id");
    PreparedStatement statement = connection.prepareStatement(query);
    statement.setLong(1, data.getRentalHeader().getId());
    statement.setObject(2, data.getReturnedAt());
    statement.setLong(3, data.getUser().getId());
    statement.setBigDecimal(4, data.getTotalPrice());

    ResultSet resultSet = statement.executeQuery();
    if (resultSet.next()) {
      Return resultReturn = new Return();
      resultReturn.setId(resultSet.getLong("id"));
      return resultReturn;
    }
    return null;
  }

  @Override
  public Return get(Return data) throws Exception {
    String query = buildSqlQuery(
        "SELECT hdr.id, hdr.receipt ",
        "FROM tb_r_return r ",
        "RIGHT JOIN tb_r_hdr_rental hdr ON hdr.id = r.rental_hdr_id ",
        "WHERE COALESCE(r.id, 0) = 0 AND hdr.id = ?;");
    PreparedStatement statement = connection.prepareStatement(query);
    statement.setLong(1, data.getRentalHeader().getId());

    ResultSet resultSet = statement.executeQuery();
    if (resultSet.next()) {
      RentalHeader header = new RentalHeader();
      header.setId(resultSet.getLong("id"));
      header.setReceipt(resultSet.getString("receipt"));

      Return result = new Return();
      result.setRentalHeader(header);
      return result;
    }
    return null;
  }

  @Override
  public Return update(Return newData) throws Exception {
    return null;
  }

  @Override
  public Return delete(Return data) throws Exception {
    return null;
  }

}
