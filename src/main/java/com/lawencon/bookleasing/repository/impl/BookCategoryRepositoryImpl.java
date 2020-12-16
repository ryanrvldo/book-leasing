package com.lawencon.bookleasing.repository.impl;

import com.lawencon.bookleasing.dao.BookCategoryDao;
import com.lawencon.bookleasing.entity.BookCategory;
import com.lawencon.bookleasing.repository.BookCategoryRepository;

/**
 * @author Rian Rivaldo Rumapea
 */
public class BookCategoryRepositoryImpl implements BookCategoryRepository {

	private final BookCategoryDao dao;

	public BookCategoryRepositoryImpl(BookCategoryDao dao) {
		this.dao = dao;
	}

	@Override
	public BookCategory add(BookCategory request) throws Exception {
		return this.dao.insert(request);
	}

	@Override
	public BookCategory get(BookCategory request) throws Exception {
		return this.dao.get(request);
	}

}
