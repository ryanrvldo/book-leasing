package com.lawencon.bookleasing.dao.jdbc.basic;

import com.lawencon.bookleasing.dao.RoleDao;
import com.lawencon.bookleasing.entity.Role;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Rian Rivaldo Rumapea
 */
public class RoleDaoImpl extends BaseDaoImpl implements RoleDao {

	@Override
	public Role insert(Role request) throws Exception {
		return null;
	}

	@Override
	public Role get(Role request) throws Exception {
		String sql = buildSQLQueryOf(
				"SELECT * FROM tb_m_role ",
				"WHERE lower(code) = lower(?)"
		);
		PreparedStatement statement = getConnection().prepareStatement(sql);
		statement.setString(1, request.getCode());

		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			Role role = new Role();
			role.setId(resultSet.getLong("id"));
			role.setCode(resultSet.getString("code"));
			role.setName(resultSet.getString("role_name"));
			return role;
		}
		return null;
	}

	@Override
	public Role update(Role request) throws Exception {
		return null;
	}

	@Override
	public Role delete(Role request) throws Exception {
		return null;
	}

}
