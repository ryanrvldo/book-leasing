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
	public Role get(Role request) throws Exception {
		return this.dao.get(request);
	}

	@Override
	public void add(Role request) throws Exception {
		throw new IllegalAccessException();
	}
}
