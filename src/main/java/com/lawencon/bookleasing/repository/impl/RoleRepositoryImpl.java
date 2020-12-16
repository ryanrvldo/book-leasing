package com.lawencon.bookleasing.repository.impl;

import com.lawencon.bookleasing.dao.RoleDao;
import com.lawencon.bookleasing.entity.Role;
import com.lawencon.bookleasing.repository.RoleRepository;

/**
 * @author Rian Rivaldo Rumapea
 */
public class RoleRepositoryImpl implements RoleRepository {

	private final RoleDao dao;

	public RoleRepositoryImpl(RoleDao dao) {
		this.dao = dao;
	}

	@Override
	public Role add(Role request) throws Exception {
		throw new IllegalAccessException();
	}

	@Override
	public Role get(Role request) throws Exception {
		return dao.get(request);
	}
}
