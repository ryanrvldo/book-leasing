package com.lawencon.bookleasing.dao.impl;

import com.lawencon.bookleasing.dao.PublisherDao;
import com.lawencon.bookleasing.entity.Publisher;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Rian Rivaldo Rumapea
 */
public class PublisherDaoImpl extends BaseDaoImpl implements PublisherDao {

	@Override
	public Publisher insert(Publisher request) throws Exception {
		String query = buildSQLQueryOf(
				"INSERT INTO tb_m_publisher(publisher_name, code, city) ",
				"VALUES (?, ?, ?) ",
				"RETURNING id ");
		PreparedStatement statement = getConnection().prepareStatement(query);
		statement.setString(1, request.getName());
		statement.setString(2, request.getCode());
		statement.setString(3, request.getCity());

		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			Publisher resultPublisher = new Publisher();
			resultPublisher.setId(resultSet.getLong("id"));
			return resultPublisher;
		}
		return null;
	}

	@Override
	public Publisher get(Publisher request) throws Exception {
		String query = buildSQLQueryOf(
				"SELECT id, code ",
				"FROM tb_m_publisher ",
				"WHERE lower(code) = lower(?) ");
		PreparedStatement statement = getConnection().prepareStatement(query);
		statement.setString(1, request.getCode());

		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			Publisher resultPublisher = new Publisher();
			resultPublisher.setId(resultSet.getLong("id"));
			resultPublisher.setCode(resultSet.getString("code"));
			return resultPublisher;
		}
		return null;
	}

	@Override
	public Publisher update(Publisher request) throws Exception {
		return null;
	}

	@Override
	public Publisher delete(Publisher request) throws Exception {
		return null;
	}

}
