package com.lawencon.bookleasing.dao.jdbc.template;

import com.lawencon.bookleasing.dao.BookCategoryDao;
import com.lawencon.bookleasing.entity.Book;
import com.lawencon.bookleasing.entity.BookCategory;
import com.lawencon.bookleasing.entity.Category;

/**
 * @author Rian Rivaldo Rumapea
 */
public class BookCategoryDaoImpl extends BaseDaoImpl implements BookCategoryDao {

	@Override
	public BookCategory insert(BookCategory request) throws Exception {
		String sql = buildSQLQueryOf(
				"INSERT INTO tb_m_book_category (book_id, category_id) ",
				"VALUES (?, ?) ",
				"RETURNING category_id");
		return queryForObject(sql,
				(rs, rowNum) -> {
					Category category = new Category();
					category.setId(rs.getLong("category_id"));
					return new BookCategory(category, request.getBook());
				},
				request.getBook().getId(),
				request.getCategory().getId()
		);
	}

	@Override
	public BookCategory get(BookCategory request) throws Exception {
		String sql = buildSQLQueryOf(
				"SELECT * FROM tb_m_book_category ",
				"WHERE book_id = ? AND category_id = ?");
		return queryForObject(sql,
				(rs, rowNum) -> {
					Book book = new Book();
					book.setId(rs.getLong("book_id"));
					Category category = new Category();
					category.setId(rs.getLong("category_id"));
					return new BookCategory(category, book);
				},
				request.getBook().getId(),
				request.getCategory().getId()
		);
	}

	@Override
	public BookCategory update(BookCategory request) throws Exception {
		return null;
	}

	@Override
	public BookCategory delete(BookCategory request) throws Exception {
		return null;
	}

}
