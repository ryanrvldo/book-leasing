package com.lawencon.bookleasing.repository.impl;

import com.lawencon.bookleasing.dao.AuthorDao;
import com.lawencon.bookleasing.entity.Author;
import com.lawencon.bookleasing.repository.AuthorRepository;

/**
 * @author Rian Rivaldo Rumapea
 */
public class AuthorRepositoryImpl implements AuthorRepository {

	private final AuthorDao dao;

	public AuthorRepositoryImpl(AuthorDao dao) {
		this.dao = dao;
	}

	@Override
	public Author add(Author request) throws Exception {
		return dao.insert(request);
	}

	@Override
	public Author get(Author request) throws Exception {
		return dao.get(request);
	}

}
