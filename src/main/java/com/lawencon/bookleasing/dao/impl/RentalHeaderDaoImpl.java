package com.lawencon.bookleasing.dao.impl;

import com.lawencon.bookleasing.dao.RentalHeaderDao;
import com.lawencon.bookleasing.entity.RentalHeader;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Rian Rivaldo Rumapea
 */
public class RentalHeaderDaoImpl extends BaseDaoImpl implements RentalHeaderDao {

	@Override
	public RentalHeader insert(RentalHeader request) throws Exception {
		String query = buildSQLQueryOf(
				"INSERT INTO tb_r_hdr_rental (receipt, customer_id, user_id) ",
				"VALUES (?, ?, ?) ",
				"RETURNING id");
		PreparedStatement statement = getConnection().prepareStatement(query);
		statement.setString(1, request.getReceipt());
		statement.setLong(2, request.getCustomer().getId());
		statement.setLong(3, request.getUser().getId());

		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			RentalHeader resultHeader = new RentalHeader();
			resultHeader.setId(resultSet.getLong("id"));
			return resultHeader;
		}
		return null;
	}

	@Override
	public RentalHeader get(RentalHeader request) throws Exception {
		String query = buildSQLQueryOf(
				"SELECT id, receipt ",
				"FROM tb_r_hdr_rental ",
				"WHERE receipt = ? ");
		PreparedStatement statement = getConnection().prepareStatement(query);
		statement.setString(1, request.getReceipt());

		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			RentalHeader resultHeader = new RentalHeader();
			resultHeader.setId(resultSet.getLong("id"));
			resultHeader.setReceipt(resultSet.getString("receipt"));
			return resultHeader;
		}
		return null;
	}

	@Override
	public RentalHeader update(RentalHeader request) throws Exception {
		return null;
	}

	@Override
	public RentalHeader delete(RentalHeader request) throws Exception {
		return null;
	}

}
