package com.lawencon.bookleasing.dao;

import java.sql.Connection;

import com.lawencon.bookleasing.config.DatabaseConnection;

/**
 * @author Rian Rivaldo Rumapea
 */
public interface BaseDao<T> {

  final Connection connection = DatabaseConnection.getConnection();

  T insert(T data) throws Exception;

  T get(T data) throws Exception;

  T update(T newData) throws Exception;

  T delete(T data) throws Exception;

  default String buildSqlQuery(String... queries) {
    if (queries.length == 0) return new String();

    StringBuilder builder = new StringBuilder();
    for (String query : queries) {
      builder.append(query);
    }
    return builder.toString();
  }

}
