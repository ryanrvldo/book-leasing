package com.lawencon.bookleasing.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lawencon.bookleasing.dao.ProfileDao;
import com.lawencon.bookleasing.model.Profile;

/**
 * @author Rian Rivaldo Rumapea
 */
public class ProfileDaoImpl implements ProfileDao {

  @Override
  public Profile insert(Profile data) throws Exception {
    String query = buildSqlQuery(
        "INSERT INTO tb_m_profile (first_name, last_name, email, phone) ",
        "VALUES (?, ?, ?, ?) ",
        "RETURNING id");
    PreparedStatement statement = connection.prepareStatement(query);
    statement.setString(1, data.getFirstName());
    statement.setString(2, data.getLastName());
    statement.setString(3, data.getEmail());
    statement.setString(4, data.getPhone());

    ResultSet resultSet = statement.executeQuery();
    if (resultSet.next()) {
      Profile profile = new Profile();
      profile.setId(resultSet.getLong("id"));
      return profile;
    }
    return null;
  }

  @Override
  public Profile get(Profile data) throws Exception {
    return null;
  }

  @Override
  public Profile update(Profile newData) throws Exception {
    return null;
  }

  @Override
  public Profile delete(Profile data) throws Exception {
    return null;
  }

}
