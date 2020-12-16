package com.lawencon.bookleasing.dao.impl;

import com.lawencon.bookleasing.config.DatabaseConnection;
import com.lawencon.bookleasing.dao.BookAuthorDao;
import com.lawencon.bookleasing.entity.Author;
import com.lawencon.bookleasing.entity.Book;
import com.lawencon.bookleasing.entity.BookAuthor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Rian Rivaldo Rumapea
 */
public class BookAuthorDaoImpl implements BookAuthorDao {

	private final Connection connection;

	public BookAuthorDaoImpl(DatabaseConnection databaseConnection) {
		this.connection = databaseConnection.getConnection();
	}

	@Override
	public BookAuthor insert(BookAuthor request) throws Exception {
		String query = buildSQLQueryOf(
				"INSERT INTO tb_m_book_author (author_id, book_id) ",
				"VALUES (?, ?) ",
				"RETURNING author_id ");
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setLong(1, request.getAuthor().getId());
		statement.setLong(2, request.getBook().getId());

		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			return new BookAuthor(new Author(resultSet.getLong("author_id")));
		}
		return null;
	}

	@Override
	public BookAuthor get(BookAuthor request) throws Exception {
		String query = buildSQLQueryOf(
				"SELECT * FROM tb_m_book_author ",
				"WHERE author_id = ? AND book_id = ? ");
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setLong(1, request.getAuthor().getId());
		statement.setLong(2, request.getBook().getId());

		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			return new BookAuthor(
					new Author(resultSet.getLong("author_id")),
					new Book(resultSet.getLong("book_id")));
		}
		return null;
	}

	@Override
	public BookAuthor update(BookAuthor request) throws Exception {
		return null;
	}

	@Override
	public BookAuthor delete(BookAuthor request) throws Exception {
		return null;
	}

}
