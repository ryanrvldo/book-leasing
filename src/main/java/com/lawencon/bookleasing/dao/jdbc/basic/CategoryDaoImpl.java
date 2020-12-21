package com.lawencon.bookleasing.dao.jdbc.basic;

import com.lawencon.bookleasing.dao.CategoryDao;
import com.lawencon.bookleasing.entity.Category;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Rian Rivaldo Rumapea
 */
public class CategoryDaoImpl extends BaseDaoImpl implements CategoryDao {

	@Override
	public Category insert(Category request) throws Exception {
		String query = buildSQLQueryOf(
				"INSERT INTO tb_m_category(category_name) ",
				"VALUES (?) ",
				"RETURNING id");
		PreparedStatement statement = getConnection().prepareStatement(query);
		statement.setString(1, request.getName());

		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			return new Category(resultSet.getLong("id"));
		}
		return null;
	}

	@Override
	public Category get(Category request) throws Exception {
		String query = buildSQLQueryOf(
				"SELECT * FROM tb_m_category ",
				"WHERE lower(category_name) = lower(?) ");
		PreparedStatement statement = getConnection().prepareStatement(query);
		statement.setString(1, request.getName());

		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			return new Category(resultSet.getLong("id"), resultSet.getString("category_name"));
		}
		return null;
	}

	@Override
	public Category update(Category newData) throws Exception {
		return null;
	}

	@Override
	public Category delete(Category data) throws Exception {
		return null;
	}

}
