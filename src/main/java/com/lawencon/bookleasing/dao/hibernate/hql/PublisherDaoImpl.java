package com.lawencon.bookleasing.dao.hibernate.hql;

import com.lawencon.bookleasing.dao.PublisherDao;
import com.lawencon.bookleasing.entity.Publisher;

import java.util.List;

/**
 * @author Rian Rivaldo Rumapea
 */
public class PublisherDaoImpl extends BaseDaoImpl<Publisher> implements PublisherDao {

	@Override
	public void insert(Publisher request) throws Exception {
		insertData(request);
	}

	@Override
	public Publisher get(Publisher request) throws Exception {
		String hql = buildSQLQueryOf(
				"FROM Publisher ",
				"WHERE lower(code) = lower(?1) ");
		List<Publisher> results = getSession()
				.createQuery(hql, Publisher.class)
				.setParameter(1, request.getCode())
				.list();
		return singleResultFromList(results);
	}

	@Override
	public void update(Publisher request) throws Exception {
		mergeUpdateData(request);
	}

	@Override
	public void delete(Publisher request) throws Exception {
		deleteData(request);
	}

	@Override
	public List<Publisher> getAll() throws Exception {
		return getAllData(Publisher.class);
	}
}
