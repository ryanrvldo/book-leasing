package com.lawencon.bookleasing.dao;

import java.util.List;

/**
 * @author Rian Rivaldo Rumapea
 */
public interface BaseDao<T> {

	void insert(T request) throws Exception;

	T get(T request) throws Exception;

	void update(T request) throws Exception;

	void delete(T request) throws Exception;

	List<T> getAll() throws Exception;

	default String buildSQLQueryOf(String... queries) {
		if (queries.length == 0) return "";

		StringBuilder builder = new StringBuilder();
		for (String query : queries) {
			builder.append(query);
		}
		return builder.toString();
	}

}
