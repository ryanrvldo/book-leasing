package com.lawencon.bookleasing.repository.impl;

import com.lawencon.bookleasing.dao.CategoryDao;
import com.lawencon.bookleasing.entity.Category;
import com.lawencon.bookleasing.repository.CategoryRepository;

/**
 * @author Rian Rivaldo Rumapea
 */
public class CategoryRepositoryImpl implements CategoryRepository {

	private final CategoryDao dao;

	public CategoryRepositoryImpl(CategoryDao dao) {
		this.dao = dao;
	}

	@Override
	public Category add(Category request) throws Exception {
		return dao.insert(request);
	}

	@Override
	public Category get(Category request) throws Exception {
		return dao.get(request);
	}

}
