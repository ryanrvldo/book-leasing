package com.lawencon.bookleasing.dao.impl;

import com.lawencon.bookleasing.dao.AuthorDao;
import com.lawencon.bookleasing.entity.Author;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Rian Rivaldo Rumapea
 */
public class AuthorDaoImpl extends BaseDaoImpl implements AuthorDao {

	@Override
	public Author insert(Author request) throws Exception {
		String query = buildSQLQueryOf(
				"INSERT INTO tb_m_author(first_name, last_name) ",
				"VALUES (?, ?) ",
				"RETURNING id");
		PreparedStatement statement = getConnection().prepareStatement(query);
		statement.setString(1, request.getFirstName());
		statement.setString(2, request.getLastName());

		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			return new Author(resultSet.getLong("id"));
		}
		return null;
	}

	@Override
	public Author get(Author request) throws Exception {
		String query = buildSQLQueryOf(
				"SELECT * FROM tb_m_author ",
				"WHERE lower(first_name) = lower(?) AND lower(last_name) = lower(?) ");
		PreparedStatement statement = getConnection().prepareStatement(query);
		statement.setString(1, request.getFirstName());
		statement.setString(2, request.getLastName());

		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			return new Author(
					resultSet.getLong("id"),
					resultSet.getString("first_name"),
					resultSet.getString("last_name"));
		}
		return null;
	}

	@Override
	public Author update(Author request) throws Exception {
		return null;
	}

	@Override
	public Author delete(Author request) throws Exception {
		return null;
	}

}
