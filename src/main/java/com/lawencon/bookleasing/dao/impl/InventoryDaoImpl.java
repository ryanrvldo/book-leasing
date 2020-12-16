package com.lawencon.bookleasing.dao.impl;

import com.lawencon.bookleasing.config.DatabaseConnection;
import com.lawencon.bookleasing.dao.InventoryDao;
import com.lawencon.bookleasing.entity.Book;
import com.lawencon.bookleasing.entity.Inventory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rian Rivaldo Rumapea
 */
public class InventoryDaoImpl implements InventoryDao {

	private final Connection connection;

	public InventoryDaoImpl(DatabaseConnection databaseConnection) {
		this.connection = databaseConnection.getConnection();
	}

	@Override
	public Inventory insert(Inventory request) throws Exception {
		String query = buildSQLQueryOf(
				"INSERT INTO tb_m_inventory (book_id) ",
				"VALUES (?) ",
				"RETURNING id ; ");
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setLong(1, request.getBook().getId());

		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			return new Inventory(resultSet.getLong("id"));
		}
		return null;
	}

	@Override
	public Inventory get(Inventory request) throws Exception {
		String query = buildSQLQueryOf(
				"SELECT i.id, b.isbn, b.rental_cost ",
				"FROM tb_m_inventory AS i ",
				"LEFT JOIN tb_m_book AS b ON b.id = i.book_id ",
				"LEFT JOIN tb_r_dtl_rental dtl ON dtl.inventory_id = i.id ",
				"LEFT JOIN tb_r_return rtr ON rtr.rental_hdr_id = dtl .rental_hdr_id ",
				"WHERE (rtr.returned_at <= now() OR dtl.id IS NULL) AND b.isbn = ? ;");
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, request.getBook().getIsbn());

		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			Book book = new Book();
			book.setIsbn(resultSet.getString("isbn"));
			book.setRentalCost(resultSet.getBigDecimal("rental_cost"));
			Inventory inventory = new Inventory();
			inventory.setId(resultSet.getLong("id"));
			inventory.setBook(book);
			return inventory;
		}
		return null;
	}

	@Override
	public Inventory update(Inventory newData) throws Exception {
		return null;
	}

	@Override
	public Inventory delete(Inventory data) throws Exception {
		return null;
	}

	@Override
	public List<Inventory> getAll() throws Exception {
		String query = buildSQLQueryOf(
				"SELECT i.id AS inventory_id, b.id AS book_id, b.title, b.rental_cost, b.isbn, b.publication_year ",
				"FROM tb_m_inventory AS i ",
				"LEFT JOIN tb_m_book AS b ON b.id = i.book_id ",
				"LEFT JOIN tb_r_dtl_rental dtl ON dtl.inventory_id = i.id ",
				"LEFT JOIN tb_r_return rtr ON rtr.rental_hdr_id = dtl.rental_hdr_id ",
				"WHERE rtr.returned_at <= now() OR dtl.id IS null;");
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(query);

		List<Inventory> resultList = new ArrayList<>();
		while (resultSet.next()) {
			Book book = new Book();
			book.setId(resultSet.getLong("book_id"));
			book.setTitle(resultSet.getString("title"));
			book.setRentalCost(resultSet.getBigDecimal("rental_cost"));
			book.setIsbn(resultSet.getString("isbn"));
			book.setPublicationYear(resultSet.getShort("publication_year"));

			Inventory inventory = new Inventory();
			inventory.setId(resultSet.getLong("inventory_id"));
			inventory.setBook(book);
			resultList.add(inventory);
		}
		if (resultList.isEmpty()) {
			return null;
		}
		return resultList;
	}

}
