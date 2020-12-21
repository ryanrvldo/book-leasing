package com.lawencon.bookleasing.dao.hibernate.hql;

import com.lawencon.bookleasing.dao.BookDao;
import com.lawencon.bookleasing.entity.Book;

import java.util.List;

/**
 * @author Rian Rivaldo Rumapea
 */
public class BookDaoImpl extends BaseDaoImpl<Book> implements BookDao {

	@Override
	public void insert(Book request) throws Exception {
		insertData(request);
	}

	@Override
	public Book get(Book request) throws Exception {
		List<Book> results = getSession()
				.createQuery("FROM Book WHERE isbn = ?1 ", Book.class)
				.setParameter(1, request.getIsbn())
				.list();
		return singleResultFromList(results);
	}

	@Override
	public void update(Book request) throws Exception {
		mergeUpdateData(request);
	}

	@Override
	public void delete(Book request) throws Exception {
		deleteData(request);
	}

	@Override
	public List<Book> getAll() throws Exception {
		return getAllData(Book.class);
	}

}
