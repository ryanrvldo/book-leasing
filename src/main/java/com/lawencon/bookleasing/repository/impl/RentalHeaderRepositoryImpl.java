package com.lawencon.bookleasing.repository.impl;

import com.lawencon.bookleasing.dao.RentalHeaderDao;
import com.lawencon.bookleasing.entity.RentalHeader;
import com.lawencon.bookleasing.repository.RentalHeaderRepository;

/**
 * @author Rian Rivaldo Rumapea
 */
public class RentalHeaderRepositoryImpl implements RentalHeaderRepository {

	private final RentalHeaderDao dao;

	public RentalHeaderRepositoryImpl(RentalHeaderDao dao) {
		this.dao = dao;
	}

	@Override
	public RentalHeader add(RentalHeader request) throws Exception {
		return this.dao.insert(request);
	}

	@Override
	public RentalHeader get(RentalHeader request) throws Exception {
		return this.dao.get(request);
	}

}
