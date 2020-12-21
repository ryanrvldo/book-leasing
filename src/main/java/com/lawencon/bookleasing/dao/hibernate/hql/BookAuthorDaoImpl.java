package com.lawencon.bookleasing.dao.hibernate.hql;

import com.lawencon.bookleasing.dao.BookAuthorDao;
import com.lawencon.bookleasing.entity.BookAuthor;

import java.util.List;

/**
 * @author Rian Rivaldo Rumapea
 */
public class BookAuthorDaoImpl extends BaseDaoImpl<BookAuthor> implements BookAuthorDao {

	@Override
	public void insert(BookAuthor request) throws Exception {
		insertData(request);
	}

	@Override
	public BookAuthor get(BookAuthor request) throws Exception {
		String hql = buildSQLQueryOf(
				"FROM BookAuthor ",
				"WHERE author.id = ?1 AND book.id = ?2 ");
		List<BookAuthor> results = getSession()
				.createQuery(hql, BookAuthor.class)
				.setParameter(1, request.getAuthor().getId())
				.setParameter(2, request.getBook().getId())
				.list();
		return singleResultFromList(results);
	}

	@Override
	public void update(BookAuthor request) throws Exception {
		mergeUpdateData(request);
	}

	@Override
	public void delete(BookAuthor request) throws Exception {
		deleteData(request);
	}

	@Override
	public List<BookAuthor> getAll() throws Exception {
		return null;
	}
}
