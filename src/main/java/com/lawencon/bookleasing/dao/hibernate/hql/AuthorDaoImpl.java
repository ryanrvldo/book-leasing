package com.lawencon.bookleasing.dao.hibernate.hql;

import com.lawencon.bookleasing.dao.AuthorDao;
import com.lawencon.bookleasing.entity.Author;

import java.util.List;

/**
 * @author Rian Rivaldo Rumapea
 */
public class AuthorDaoImpl extends BaseDaoImpl<Author> implements AuthorDao {

	@Override
	public void insert(Author data) throws Exception {
		insertData(data);
	}

	@Override
	public Author get(Author data) throws Exception {
		List<Author> resultList = getSession()
				.createQuery("from Author where lower(firstName) = lower(?1) and lower(lastName) = lower(?2) ", Author.class)
				.setParameter(1, data.getFirstName())
				.setParameter(2, data.getLastName())
				.list();
		return singleResultFromList(resultList);
	}

	@Override
	public void update(Author data) throws Exception {
		mergeUpdateData(data);
	}

	@Override
	public void delete(Author data) throws Exception {
		deleteData(data);
	}

	@Override
	public List<Author> getAll() throws Exception {
		return getAllData(Author.class);
	}

}
