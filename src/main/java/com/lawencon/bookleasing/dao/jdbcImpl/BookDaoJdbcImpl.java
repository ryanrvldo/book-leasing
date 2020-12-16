package com.lawencon.bookleasing.dao.jdbcImpl;

import com.lawencon.bookleasing.dao.BookDao;
import com.lawencon.bookleasing.entity.Book;

/**
 * @author Rian Rivaldo Rumapea
 */
public class BookDaoJdbcImpl extends BaseDaoJdbcImpl implements BookDao {

	@Override
	public Book insert(Book request) throws Exception {
		String sql = buildSQLQueryOf(
				"INSERT INTO tb_m_book (title, isbn, publication_year, publisher_id, language_id, rental_cost, replacement_cost) ",
				"VALUES (?, ?, ?, ?, ?, ?, ?) ",
				"RETURNING id");
		return queryForObject(sql,
				(rs, rowNum) -> {
					Book book = new Book();
					book.setId(rs.getLong("id"));
					return book;
				},
				request.getTitle(),
				request.getIsbn(),
				request.getPublicationYear(),
				request.getPublisher().getId(),
				request.getLanguage().getId(),
				request.getRentalCost(),
				request.getReplacementCost()
		);
	}

	@Override
	public Book get(Book request) throws Exception {
		return null;
	}

	@Override
	public Book update(Book request) throws Exception {
		return null;
	}

	@Override
	public Book delete(Book request) throws Exception {
		return null;
	}

}
