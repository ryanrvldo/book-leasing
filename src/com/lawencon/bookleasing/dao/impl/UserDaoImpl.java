package com.lawencon.bookleasing.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lawencon.bookleasing.dao.UserDao;
import com.lawencon.bookleasing.model.Profile;
import com.lawencon.bookleasing.model.Role;
import com.lawencon.bookleasing.model.User;

/**
 * @author Rian Rivaldo Rumapea
 */
public class UserDaoImpl implements UserDao {

  @Override
  public User get(User data) throws Exception {
    String query = buildSqlQuery(
        "SELECT u.id AS user_id, u.username, u.is_active, u.profile_id, u.role_id, r.code, r.role_name ",
        "FROM tb_m_user AS u ",
        "INNER JOIN tb_m_role AS r ON r.id = u.role_id ",
        "WHERE u.username = ? AND u.user_password = ? ");
    PreparedStatement statement = connection.prepareStatement(query);
    statement.setString(1, data.getUsername());
    statement.setString(2, data.getPassword());

    ResultSet resultSet = statement.executeQuery();
    if (resultSet.next()) {
      Profile profile = new Profile();
      profile.setId(resultSet.getLong("profile_id"));

      Role role = new Role();
      role.setId(resultSet.getLong("role_id"));
      role.setCode(resultSet.getString("code"));
      role.setName(resultSet.getString("role_name"));

      User user = new User();
      user.setId(resultSet.getLong("user_id"));
      user.setUsername(resultSet.getString("username"));
      user.setIsActive(resultSet.getBoolean("is_active"));
      user.setProfile(profile);
      user.setRole(role);
      return user;
    }
    return null;
  }

  @Override
  public User insert(User data) throws Exception {
    String query = buildSqlQuery(
        "INSERT INTO tb_m_user (username, user_password, is_active, profile_id, role_id) ",
        "VALUES (?, ?, ?, ?, ?) ",
        "RETURNING id ");
    PreparedStatement statement = connection.prepareStatement(query);
    statement.setString(1, data.getUsername());
    statement.setString(2, data.getPassword());
    statement.setBoolean(3, data.getIsActive());
    statement.setLong(4, data.getProfile().getId());
    statement.setLong(5, data.getRole().getId());

    ResultSet resultSet = statement.executeQuery();
    if (resultSet.next()) {
      return new User(resultSet.getLong("id"));
    }
    return null;
  }

  @Override
  public User update(User newData) throws Exception {
    return null;
  }

  @Override
  public User delete(User data) throws Exception {
    return null;
  }

}
