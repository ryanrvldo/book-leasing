package com.lawencon.bookleasing.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lawencon.bookleasing.dao.LanguageDao;
import com.lawencon.bookleasing.model.Language;

/**
 * @author Rian Rivaldo Rumapea
 */
public class LanguageDaoImpl implements LanguageDao {

  @Override
  public Language insert(Language data) throws Exception {
    String query = buildSqlQuery(
        "INSERT INTO tb_m_language (code, language_name) ",
        "VALUES (?, ?) ",
        "RETURNING id");
    PreparedStatement statement = connection.prepareStatement(query);
    statement.setString(1, data.getCode());
    statement.setString(2, data.getName());

    ResultSet resultSet = statement.executeQuery();
    if (resultSet.next()) {
      return new Language(resultSet.getLong("id"));
    }
    return null;
  }

  @Override
  public Language get(Language data) throws Exception {
    String query = buildSqlQuery(
        "SELECT id, code ",
        "FROM tb_m_language ",
        "WHERE lower(code) = lower(?) ");
    PreparedStatement statement = connection.prepareStatement(query);
    statement.setString(1, data.getCode());

    ResultSet resultSet = statement.executeQuery();
    if (resultSet.next()) {
      return new Language(resultSet.getLong("id"), resultSet.getString("code"));
    }
    return null;
  }

  @Override
  public Language update(Language newData) throws Exception {
    return null;
  }

  @Override
  public Language delete(Language data) throws Exception {
    return null;
  }

}
