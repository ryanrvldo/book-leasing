package com.lawencon.bookleasing.repository.impl;

import com.lawencon.bookleasing.dao.RentalDetailDao;
import com.lawencon.bookleasing.entity.RentalDetail;
import com.lawencon.bookleasing.repository.RentalDetailRepository;

/**
 * @author Rian Rivaldo Rumapea
 */
public class RentalDetailRepositoryImpl implements RentalDetailRepository {

	private final RentalDetailDao dao;

	public RentalDetailRepositoryImpl(RentalDetailDao dao) {
		this.dao = dao;
	}

	@Override
	public RentalDetail add(RentalDetail request) throws Exception {
		return this.dao.insert(request);
	}

	@Override
	public RentalDetail get(RentalDetail request) throws Exception {
		throw new IllegalAccessException("Not implemented yet. Under development.");
	}

}
