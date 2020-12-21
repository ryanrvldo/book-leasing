package com.lawencon.bookleasing.dao.hibernate.hql;

import com.lawencon.bookleasing.dao.RentalHeaderDao;
import com.lawencon.bookleasing.entity.RentalHeader;

import java.util.List;

/**
 * @author Rian Rivaldo Rumapea
 */
public class RentalHeaderDaoImpl extends BaseDaoImpl<RentalHeader> implements RentalHeaderDao {

	@Override
	public void insert(RentalHeader request) throws Exception {
		insertData(request);
	}

	@Override
	public RentalHeader get(RentalHeader request) throws Exception {
		String hql = buildSQLQueryOf(
				"FROM RentalHeader ",
				"WHERE receipt = ?1 "
		);
		List<RentalHeader> results = getSession()
				.createQuery(hql, RentalHeader.class)
				.setParameter(1, request.getReceipt())
				.list();
		return singleResultFromList(results);
	}

	@Override
	public void update(RentalHeader request) throws Exception {
		mergeUpdateData(request);
	}

	@Override
	public void delete(RentalHeader request) throws Exception {
		deleteData(request);
	}

	@Override
	public List<RentalHeader> getAll() throws Exception {
		return getAllData(RentalHeader.class);
	}
}
