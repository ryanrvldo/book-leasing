package com.lawencon.bookleasing.dao.impl;

import com.lawencon.bookleasing.config.DatabaseConnection;
import com.lawencon.bookleasing.dao.BookCategoryDao;
import com.lawencon.bookleasing.entity.Book;
import com.lawencon.bookleasing.entity.BookCategory;
import com.lawencon.bookleasing.entity.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Rian Rivaldo Rumapea
 */
public class BookCategoryDaoImpl implements BookCategoryDao {

	private final Connection connection;

	public BookCategoryDaoImpl(DatabaseConnection databaseConnection) {
		this.connection = databaseConnection.getConnection();
	}

	@Override
	public BookCategory insert(BookCategory request) throws Exception {
		String query = buildSQLQueryOf(
				"INSERT INTO tb_m_book_category (book_id, category_id) ",
				"VALUES (?, ?) ",
				"RETURNING category_id");
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setLong(1, request.getBook().getId());
		statement.setLong(2, request.getCategory().getId());

		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			return new BookCategory(new Category(resultSet.getLong("category_id")));
		}
		return null;
	}

	@Override
	public BookCategory get(BookCategory request) throws Exception {
		String query = buildSQLQueryOf(
				"SELECT * FROM tb_m_book_category ",
				"WHERE book_id = ? AND category_id = ?");
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setLong(1, request.getBook().getId());
		statement.setLong(2, request.getCategory().getId());

		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			return new BookCategory(
					new Category(resultSet.getLong("category_id")),
					new Book(resultSet.getLong("book_id")));
		}
		return null;
	}

	@Override
	public BookCategory update(BookCategory request) throws Exception {
		return null;
	}

	@Override
	public BookCategory delete(BookCategory request) throws Exception {
		return null;
	}

}
