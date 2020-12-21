package com.lawencon.bookleasing.dao.hibernate.hql;

import com.lawencon.bookleasing.dao.BookCategoryDao;
import com.lawencon.bookleasing.entity.BookCategory;

import java.util.List;

/**
 * @author Rian Rivaldo Rumapea
 */
public class BookCategoryDaoImpl extends BaseDaoImpl<BookCategory> implements BookCategoryDao {

	@Override
	public void insert(BookCategory request) throws Exception {
		insertData(request);
	}

	@Override
	public BookCategory get(BookCategory request) throws Exception {
		String sql = buildSQLQueryOf(
				"FROM BookCategory ",
				"WHERE book.id = ?1 AND category.id = ?2");
		List<BookCategory> results = getSession()
				.createQuery(sql, BookCategory.class)
				.setParameter(1, request.getBook().getId())
				.setParameter(2, request.getCategory().getId())
				.list();
		return (results != null ? results.size() : 0) > 0 ? results.get(0) : null;
	}

	@Override
	public void update(BookCategory request) throws Exception {
		mergeUpdateData(request);
	}

	@Override
	public void delete(BookCategory request) throws Exception {
		deleteData(request);
	}

	@Override
	public List<BookCategory> getAll() throws Exception {
		return null;
	}
}
