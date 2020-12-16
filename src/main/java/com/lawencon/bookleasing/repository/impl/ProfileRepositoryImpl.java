package com.lawencon.bookleasing.repository.impl;

import com.lawencon.bookleasing.dao.ProfileDao;
import com.lawencon.bookleasing.entity.Profile;
import com.lawencon.bookleasing.repository.ProfileRepository;

/**
 * @author Rian Rivaldo Rumapea
 */
public class ProfileRepositoryImpl implements ProfileRepository {

	private final ProfileDao dao;

	public ProfileRepositoryImpl(ProfileDao dao) {
		this.dao = dao;
	}

	@Override
	public Profile add(Profile request) throws Exception {
		return this.dao.insert(request);
	}

	@Override
	public Profile get(Profile request) throws Exception {
		throw new IllegalAccessException("Not implemented yet. Under development.");
	}

}
