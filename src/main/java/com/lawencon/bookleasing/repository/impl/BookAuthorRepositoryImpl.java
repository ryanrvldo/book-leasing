package com.lawencon.bookleasing.repository.impl;

import com.lawencon.bookleasing.dao.BookAuthorDao;
import com.lawencon.bookleasing.entity.BookAuthor;
import com.lawencon.bookleasing.repository.BookAuthorRepository;

/**
 * @author Rian Rivaldo Rumapea
 */
public class BookAuthorRepositoryImpl implements BookAuthorRepository {

	private final BookAuthorDao dao;

	public BookAuthorRepositoryImpl(BookAuthorDao dao) {
		this.dao = dao;
	}

	@Override
	public BookAuthor add(BookAuthor request) throws Exception {
		return this.dao.insert(request);
	}

	@Override
	public BookAuthor get(BookAuthor request) throws Exception {
		return this.dao.get(request);
	}

}
