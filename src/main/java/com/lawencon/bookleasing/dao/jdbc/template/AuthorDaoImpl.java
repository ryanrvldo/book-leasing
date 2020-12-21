package com.lawencon.bookleasing.dao.jdbc.template;

import com.lawencon.bookleasing.dao.AuthorDao;
import com.lawencon.bookleasing.entity.Author;

import java.util.List;

/**
 * @author Rian Rivaldo Rumapea
 */
public class AuthorDaoImpl extends BaseDaoImpl<Author> implements AuthorDao {

	@Override
	public void insert(Author request) throws Exception {
		String sql = buildSQLQueryOf(
				"INSERT INTO tb_m_author(first_name, last_name) ",
				"VALUES (?, ?) ",
				"RETURNING id");
		queryForObject(sql,
				(rs, rowNum) -> {
					Author author = new Author();
					author.setId(rs.getLong("id"));
					return author;
				},
				request.getFirstName(), request.getLastName());
	}

	@Override
	public Author get(Author request) throws Exception {
		String sql = buildSQLQueryOf(
				"SELECT * FROM tb_m_author ",
				"WHERE lower(first_name) = lower(?) AND lower(last_name) = lower(?) ");
		return queryForObject(sql,
				(rs, rowNum) -> {
					Author author = new Author();
					author.setId(rs.getLong("id"));
					author.setFirstName(rs.getString("first_name"));
					author.setLastName(rs.getString("last_name"));
					return author;
				},
				request.getFirstName(),
				request.getLastName());
	}

	@Override
	public void update(Author request) throws Exception {
	}

	@Override
	public void delete(Author request) throws Exception {
	}

	@Override
	public List<Author> getAll() throws Exception {
		return null;
	}

}
