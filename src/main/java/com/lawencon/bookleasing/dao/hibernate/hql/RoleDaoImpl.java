package com.lawencon.bookleasing.dao.hibernate.hql;

import com.lawencon.bookleasing.dao.RoleDao;
import com.lawencon.bookleasing.entity.Role;

import java.util.List;

/**
 * @author Rian Rivaldo Rumapea
 */
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {

	@Override
	public void insert(Role request) throws Exception {
		insertData(request);
	}

	@Override
	public Role get(Role request) throws Exception {
		String hql = buildSQLQueryOf(
				"FROM Role ",
				"WHERE lower(code) = lower(?1) "
		);
		List<Role> results = getSession()
				.createQuery(hql, Role.class)
				.setParameter(1, request.getCode())
				.list();
		return singleResultFromList(results);
	}

	@Override
	public void update(Role request) throws Exception {
		mergeUpdateData(request);
	}

	@Override
	public void delete(Role request) throws Exception {
		deleteData(request);
	}

	@Override
	public List<Role> getAll() throws Exception {
		return getAllData(Role.class);
	}

}
