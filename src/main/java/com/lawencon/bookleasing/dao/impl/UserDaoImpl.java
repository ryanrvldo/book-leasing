package com.lawencon.bookleasing.dao.impl;

import com.lawencon.bookleasing.dao.UserDao;
import com.lawencon.bookleasing.entity.Profile;
import com.lawencon.bookleasing.entity.Role;
import com.lawencon.bookleasing.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Rian Rivaldo Rumapea
 */
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

	@Override
	public User insert(User request) throws Exception {
		String query = buildSQLQueryOf(
				"INSERT INTO tb_m_user (username, user_password, is_active, profile_id, role_id) ",
				"VALUES (?, crypt(?, gen_salt('bf')), ?, ?, ?) ",
				"RETURNING id ");
		PreparedStatement statement = getConnection().prepareStatement(query);
		statement.setString(1, request.getUsername());
		statement.setString(2, request.getPassword());
		statement.setBoolean(3, request.getIsActive());
		statement.setLong(4, request.getProfile().getId());
		statement.setLong(5, request.getRole().getId());

		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			User user = new User();
			user.setId(resultSet.getLong("id"));
			user.setUsername(request.getUsername());
			user.setIsActive(request.getIsActive());
			user.setProfile(request.getProfile());
			user.setRole(request.getRole());
			return user;
		}
		return null;
	}

	@Override
	public User get(User request) throws Exception {
		String query = buildSQLQueryOf(
				"SELECT u.id AS user_id, u.username, u.is_active, u.profile_id, u.role_id, r.code, r.role_name ",
				"FROM tb_m_user AS u ",
				"INNER JOIN tb_m_role AS r ON r.id = u.role_id ",
				"WHERE lower(u.username) = lower(?) AND u.user_password = crypt(?, u.user_password) ");
		PreparedStatement statement = getConnection().prepareStatement(query);
		statement.setString(1, request.getUsername());
		statement.setString(2, request.getPassword());

		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			Profile profile = new Profile();
			profile.setId(resultSet.getLong("profile_id"));

			Role role = new Role();
			role.setId(resultSet.getLong("role_id"));
			role.setCode(resultSet.getString("code"));
			role.setName(resultSet.getString("role_name"));

			User user = new User();
			user.setId(resultSet.getLong("user_id"));
			user.setUsername(resultSet.getString("username"));
			user.setIsActive(resultSet.getBoolean("is_active"));
			user.setProfile(profile);
			user.setRole(role);
			return user;
		}
		return null;
	}

	@Override
	public User update(User request) throws Exception {
		return null;
	}

	@Override
	public User delete(User request) throws Exception {
		return null;
	}

}
