package com.lawencon.bookleasing.dao.hibernate.hql;

import com.lawencon.bookleasing.dao.UserDao;
import com.lawencon.bookleasing.entity.User;

import java.util.List;

/**
 * @author Rian Rivaldo Rumapea
 */
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	@Override
	public void insert(User request) throws Exception {
		insertData(request);
	}

	@Override
	public User get(User request) throws Exception {
		String sql = "FROM User WHERE lower(username) = lower(?1) AND password = ?2 ";
		List<User> results = getSession()
				.createQuery(sql, User.class)
				.setParameter(1, request.getUsername())
				.setParameter(2, request.getPassword())
				.list();
		return singleResultFromList(results);
	}

	@Override
	public void update(User request) throws Exception {
		mergeUpdateData(request);
	}

	@Override
	public void delete(User request) throws Exception {
		deleteData(request);
	}

	@Override
	public List<User> getAll() throws Exception {
		return getAllData(User.class);
	}
}
