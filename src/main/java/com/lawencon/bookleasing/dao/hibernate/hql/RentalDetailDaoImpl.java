package com.lawencon.bookleasing.dao.hibernate.hql;

import com.lawencon.bookleasing.dao.RentalDetailDao;
import com.lawencon.bookleasing.entity.RentalDetail;

import java.util.List;

/**
 * @author Rian Rivaldo Rumapea
 */
public class RentalDetailDaoImpl extends BaseDaoImpl<RentalDetail> implements RentalDetailDao {

	@Override
	public void insert(RentalDetail request) throws Exception {
		insertData(request);
	}

	@Override
	public RentalDetail get(RentalDetail request) throws Exception {
		return null;
	}

	@Override
	public void update(RentalDetail request) throws Exception {
		mergeUpdateData(request);
	}

	@Override
	public void delete(RentalDetail request) throws Exception {
		deleteData(request);
	}

	@Override
	public List<RentalDetail> getAll() throws Exception {
		return getAllData(RentalDetail.class);
	}
}
