package com.lawencon.bookleasing.dao.hibernate.hql;

import com.lawencon.bookleasing.dao.CategoryDao;
import com.lawencon.bookleasing.entity.Category;

import java.util.List;

/**
 * @author Rian Rivaldo Rumapea
 */
public class CategoryDaoImpl extends BaseDaoImpl<Category> implements CategoryDao {

	@Override
	public void insert(Category request) throws Exception {
		insertData(request);
	}

	@Override
	public Category get(Category request) throws Exception {
		List<Category> results = getSession()
				.createQuery("FROM Category WHERE lower(name) = lower(?1) ", Category.class)
				.setParameter(1, request.getName())
				.list();
		return singleResultFromList(results);
	}

	@Override
	public void update(Category request) throws Exception {
		mergeUpdateData(request);
	}

	@Override
	public void delete(Category request) throws Exception {
		deleteData(request);
	}

	@Override
	public List<Category> getAll() throws Exception {
		return getSession()
				.createQuery("FROM Category ", Category.class)
				.list();
	}
}
