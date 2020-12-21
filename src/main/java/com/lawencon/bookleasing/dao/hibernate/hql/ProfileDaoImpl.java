package com.lawencon.bookleasing.dao.hibernate.hql;

import com.lawencon.bookleasing.dao.ProfileDao;
import com.lawencon.bookleasing.entity.Profile;

import java.util.List;

/**
 * @author Rian Rivaldo Rumapea
 */
public class ProfileDaoImpl extends BaseDaoImpl<Profile> implements ProfileDao {

	@Override
	public void insert(Profile request) throws Exception {
		insertData(request);
	}

	@Override
	public Profile get(Profile request) throws Exception {
		String hql = buildSQLQueryOf(
				"FROM Profile ",
				"WHERE lower(firstName) = ?1 AND lower(email) = ?2"
		);
		List<Profile> results = getSession()
				.createQuery(hql, Profile.class)
				.setParameter(1, request.getFirstName())
				.setParameter(2, request.getEmail())
				.list();
		return singleResultFromList(results);
	}

	@Override
	public void update(Profile request) throws Exception {
		mergeUpdateData(request);
	}

	@Override
	public void delete(Profile request) throws Exception {
		deleteData(request);
	}

	@Override
	public List<Profile> getAll() throws Exception {
		return getAllData(Profile.class);
	}
}
