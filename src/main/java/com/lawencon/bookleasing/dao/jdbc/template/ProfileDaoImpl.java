package com.lawencon.bookleasing.dao.jdbc.template;

import com.lawencon.bookleasing.dao.ProfileDao;
import com.lawencon.bookleasing.entity.Profile;

/**
 * @author Rian Rivaldo Rumapea
 */
public class ProfileDaoImpl extends BaseDaoImpl implements ProfileDao {

	@Override
	public Profile insert(Profile request) throws Exception {
		String sql = buildSQLQueryOf(
				"INSERT INTO tb_m_profile (first_name, last_name, email, phone) ",
				"VALUES (?, ?, ?, ?) ",
				"RETURNING id ");
		return queryForObject(sql,
				(rs, rowNum) -> {
					Profile profile = new Profile();
					profile.setId(rs.getLong("id"));
					return profile;
				},
				request.getFirstName(),
				request.getLastName(),
				request.getEmail(),
				request.getPhone()
		);
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
