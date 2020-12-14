package com.lawencon.bookleasing.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lawencon.bookleasing.dao.RentalDetailDao;
import com.lawencon.bookleasing.model.RentalDetail;

/**
 * @author Rian Rivaldo Rumapea
 */
public class RentalDetailDaoImpl implements RentalDetailDao {

  @Override
  public RentalDetail insert(RentalDetail data) throws Exception {
    String query = buildSqlQuery(
        "INSERT INTO tb_r_dtl_rental (rental_hdr_id, inventory_id, total_price, rental_date, return_date) ",
        "VALUES (?, ?, ?, ?, ?) ", "RETURNING id");
    PreparedStatement statement = connection.prepareStatement(query);
    statement.setLong(1, data.getRentalHeader().getId());
    statement.setLong(2, data.getInventory().getId());
    statement.setBigDecimal(3, data.getTotalPrice());
    statement.setObject(4, data.getRentalDate());
    statement.setObject(5, data.getReturnDate());

    RentalDetail result = new RentalDetail();
    ResultSet resultSet = statement.executeQuery();
    if (resultSet.next()) {
      result.setId(resultSet.getLong("id"));
      return result;
    }
    return null;
  }

  @Override
  public RentalDetail get(RentalDetail data) throws Exception {
    return null;
  }

  @Override
  public RentalDetail update(RentalDetail newData) throws Exception {
    return null;
  }

  @Override
  public RentalDetail delete(RentalDetail data) throws Exception {
    return null;
  }

}
