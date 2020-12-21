package com.lawencon.bookleasing.repository.impl;

import com.lawencon.bookleasing.dao.BookDao;
import com.lawencon.bookleasing.entity.Book;
import com.lawencon.bookleasing.repository.BookRepository;

/**
 * @author Rian Rivaldo Rumapea
 */
public class BookRepositoryImpl implements BookRepository {

	private final BookDao dao;

	public BookRepositoryImpl(BookDao dao) {
		this.dao = dao;
	}

	@Override
	public void add(Book request) throws Exception {
		this.dao.insert(request);
	}

	@Override
	public Book get(Book request) throws Exception {
		throw new IllegalAccessException();
	}

}
