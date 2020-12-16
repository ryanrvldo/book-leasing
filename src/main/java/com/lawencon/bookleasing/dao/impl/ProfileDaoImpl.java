package com.lawencon.bookleasing.dao.impl;

import com.lawencon.bookleasing.dao.ProfileDao;
import com.lawencon.bookleasing.entity.Profile;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Rian Rivaldo Rumapea
 */
public class ProfileDaoImpl extends BaseDaoImpl implements ProfileDao {

	@Override
	public Profile insert(Profile request) throws Exception {
		String query = buildSQLQueryOf(
				"INSERT INTO tb_m_profile (first_name, last_name, email, phone) ",
				"VALUES (?, ?, ?, ?) ",
				"RETURNING id ");
		PreparedStatement statement = getConnection().prepareStatement(query);
		statement.setString(1, request.getFirstName());
		statement.setString(2, request.getLastName());
		statement.setString(3, request.getEmail());
		statement.setString(4, request.getPhone());

		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			Profile profile = new Profile();
			profile.setId(resultSet.getLong("id"));
			return profile;
		}
		return null;
	}

	@Override
	public Profile get(Profile request) throws Exception {
		return null;
	}

	@Override
	public Profile update(Profile request) throws Exception {
		return null;
	}

	@Override
	public Profile delete(Profile request) throws Exception {
		return null;
	}

}
