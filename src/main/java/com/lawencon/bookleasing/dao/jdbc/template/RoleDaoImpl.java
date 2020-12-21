package com.lawencon.bookleasing.dao.jdbc.template;

import com.lawencon.bookleasing.dao.RoleDao;
import com.lawencon.bookleasing.entity.Role;

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
		return queryForObject(sql,
				(rs, rowNum) -> {
					Role role = new Role();
					role.setId(rs.getLong("id"));
					role.setCode(rs.getString("code"));
					role.setName(rs.getString("role_name"));
					return role;
				},
				request.getCode()
		);
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
