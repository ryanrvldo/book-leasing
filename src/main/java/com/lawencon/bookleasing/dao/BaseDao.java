package com.lawencon.bookleasing.dao;

import java.util.List;

/**
 * @author Rian Rivaldo
 */

public interface BaseDao<T> {

  T findById(Long id) throws Exception;

  void insert(T data) throws Exception;

  void update(T data) throws Exception;

  void delete(T data) throws Exception;

  void deleteById(Long id) throws Exception;

  List<T> findAll() throws Exception;

  default String buildQueryOf(String... queries) {
	if (queries.length == 0) return "";

	StringBuilder builder = new StringBuilder();
	for (String query : queries) {
	  builder.append(query);
	}
	return builder.toString();
  }

}
