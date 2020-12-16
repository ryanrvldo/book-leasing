package com.lawencon.bookleasing.dao.jdbcImpl;

import com.lawencon.bookleasing.dao.CategoryDao;
import com.lawencon.bookleasing.entity.Category;

/**
 * @author Rian Rivaldo Rumapea
 */
public class CategoryDaoJdbcImpl extends BaseDaoJdbcImpl implements CategoryDao {

	@Override
	public Category insert(Category request) throws Exception {
		String sql = buildSQLQueryOf(
				"INSERT INTO tb_m_category(category_name) ",
				"VALUES (?) ",
				"RETURNING id");
		return queryForObject(sql,
				(rs, rowNum) -> {
					Category category = new Category();
					category.setId(rs.getLong("id"));
					return category;
				},
				request.getName()
		);
	}

	@Override
	public Category get(Category request) throws Exception {
		String sql = buildSQLQueryOf(
				"SELECT * FROM tb_m_category ",
				"WHERE lower(category_name) = lower(?) ");
		return queryForObject(sql,
				(rs, rowNum) -> {
					Category category = new Category();
					category.setId(rs.getLong("id"));
					category.setName(rs.getString("category_name"));
					return category;
				},
				request.getName()
		);
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
