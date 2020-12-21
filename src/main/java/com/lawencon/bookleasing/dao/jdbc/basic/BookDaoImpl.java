package com.lawencon.bookleasing.dao.jdbc.basic;

import com.lawencon.bookleasing.dao.BookDao;
import com.lawencon.bookleasing.entity.Book;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Rian Rivaldo Rumapea
 */
public class BookDaoImpl extends BaseDaoImpl implements BookDao {

	@Override
	public Book insert(Book request) throws Exception {
		String query = buildSQLQueryOf(
				"INSERT INTO tb_m_book (title, isbn, publication_year, publisher_id, language_id, rental_cost, replacement_cost) ",
				"VALUES (?, ?, ?, ?, ?, ?, ?) ",
				"RETURNING id");
		PreparedStatement statement = getConnection().prepareStatement(query);
		statement.setString(1, request.getTitle());
		statement.setString(2, request.getIsbn());
		statement.setShort(3, request.getPublicationYear());
		statement.setLong(4, request.getPublisher().getId());
		statement.setLong(5, request.getLanguage().getId());
		statement.setBigDecimal(6, request.getRentalCost());
		statement.setBigDecimal(7, request.getReplacementCost());

		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			return new Book(resultSet.getLong("id"));
		}
		return null;
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
