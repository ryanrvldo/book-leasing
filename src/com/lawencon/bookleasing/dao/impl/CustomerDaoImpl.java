package com.lawencon.bookleasing.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lawencon.bookleasing.dao.CustomerDao;
import com.lawencon.bookleasing.model.Customer;

/**
 * @author Rian Rivaldo Rumapea
 */
public class CustomerDaoImpl implements CustomerDao {

  @Override
  public Customer insert(Customer data) throws Exception {
    String query = buildSqlQuery(
        "INSERT INTO tb_m_customer (profile_id) ",
        "VALUES (?) ",
        "RETURNING id");
    PreparedStatement statement = connection.prepareStatement(query);
    statement.setLong(1, data.getProfile().getId());

    ResultSet resultSet = statement.executeQuery();
    if (resultSet.next()) {
      Customer customer = new Customer();
      customer.setId(resultSet.getLong("id"));
      return customer;
    }
    return null;
  }

  @Override
  public Customer get(Customer data) throws Exception {
    return null;
  }

  @Override
  public Customer update(Customer newData) throws Exception {
    return null;
  }

  @Override
  public Customer delete(Customer data) throws Exception {
    return null;
  }

}
