package com.lawencon.bookleasing.dao.jdbc.template;

import com.lawencon.bookleasing.dao.UserDao;
import com.lawencon.bookleasing.entity.Profile;
import com.lawencon.bookleasing.entity.Role;
import com.lawencon.bookleasing.entity.User;

/**
 * @author Rian Rivaldo Rumapea
 */
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

	@Override
	public User insert(User request) throws Exception {
		String sql = buildSQLQueryOf(
				"INSERT INTO tb_m_user (username, user_password, is_active, profile_id, role_id) ",
				"VALUES (?, crypt(?, gen_salt('bf')), ?, ?, ?) ",
				"RETURNING id ");
		return queryForObject(sql,
				(rs, rowNum) -> {
					User user = new User();
					user.setId(rs.getLong("id"));
					user.setUsername(request.getUsername());
					user.setIsActive(request.getIsActive());
					user.setProfile(request.getProfile());
					user.setRole(request.getRole());
					return user;
				},
				request.getUsername(),
				request.getPassword(),
				request.getIsActive(),
				request.getProfile().getId(),
				request.getRole().getId()
		);
	}

	@Override
	public User get(User request) throws Exception {
		String sql = buildSQLQueryOf(
				"SELECT u.id AS user_id, u.username, u.is_active, u.profile_id, u.role_id, r.code, r.role_name ",
				"FROM tb_m_user AS u ",
				"INNER JOIN tb_m_role AS r ON r.id = u.role_id ",
				"WHERE lower(u.username) = lower(?) AND u.user_password = crypt(?, u.user_password) ");

		return queryForObject(sql,
				(rs, rowNum) -> {
					Profile profile = new Profile();
					profile.setId(rs.getLong("profile_id"));

					Role role = new Role();
					role.setId(rs.getLong("role_id"));
					role.setCode(rs.getString("code"));
					role.setName(rs.getString("role_name"));

					User user = new User();
					user.setId(rs.getLong("user_id"));
					user.setUsername(rs.getString("username"));
					user.setIsActive(rs.getBoolean("is_active"));
					user.setProfile(profile);
					user.setRole(role);
					return user;
				},
				request.getUsername(),
				request.getPassword()
		);
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
