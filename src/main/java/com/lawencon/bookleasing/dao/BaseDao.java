package com.lawencon.bookleasing.dao;

/**
 * @author Rian Rivaldo Rumapea
 */
public interface BaseDao<T> {

  T insert(T request) throws Exception;

  T get(T request) throws Exception;

  T update(T request) throws Exception;

  T delete(T request) throws Exception;

  default String buildSQLQueryOf(String... queries) {
    if (queries.length == 0) return "";

    StringBuilder builder = new StringBuilder();
    for (String query : queries) {
      builder.append(query);
    }
    return builder.toString();
  }

}
