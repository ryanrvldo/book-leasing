package com.lawencon.bookleasing.dao.impl;

import com.lawencon.bookleasing.config.DatabaseConnection;
import com.lawencon.bookleasing.dao.RentalDetailDao;
import com.lawencon.bookleasing.entity.RentalDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Rian Rivaldo Rumapea
 */
public class RentalDetailDaoImpl implements RentalDetailDao {

	private final Connection connection;

	public RentalDetailDaoImpl(DatabaseConnection databaseConnection) {
		this.connection = databaseConnection.getConnection();
	}

	@Override
	public RentalDetail insert(RentalDetail request) throws Exception {
		String query = buildSQLQueryOf(
				"INSERT INTO tb_r_dtl_rental (rental_hdr_id, inventory_id, total_price, rental_date, return_date) ",
				"VALUES (?, ?, ?, ?, ?) ", "RETURNING id");
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setLong(1, request.getRentalHeader().getId());
		statement.setLong(2, request.getInventory().getId());
		statement.setBigDecimal(3, request.getTotalPrice());
		statement.setObject(4, request.getRentalDate());
		statement.setObject(5, request.getReturnDate());

		RentalDetail result = new RentalDetail();
		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			result.setId(resultSet.getLong("id"));
			return result;
		}
		return null;
	}

	@Override
	public RentalDetail get(RentalDetail request) throws Exception {
		return null;
	}

	@Override
	public RentalDetail update(RentalDetail request) throws Exception {
		return null;
	}

	@Override
	public RentalDetail delete(RentalDetail request) throws Exception {
		return null;
	}

}
