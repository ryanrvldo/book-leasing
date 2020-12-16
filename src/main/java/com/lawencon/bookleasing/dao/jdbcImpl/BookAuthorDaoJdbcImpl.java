package com.lawencon.bookleasing.dao.jdbcImpl;

import com.lawencon.bookleasing.dao.BookAuthorDao;
import com.lawencon.bookleasing.entity.Author;
import com.lawencon.bookleasing.entity.Book;
import com.lawencon.bookleasing.entity.BookAuthor;

/**
 * @author Rian Rivaldo Rumapea
 */
public class BookAuthorDaoJdbcImpl extends BaseDaoJdbcImpl implements BookAuthorDao {

	@Override
	public BookAuthor insert(BookAuthor request) throws Exception {
		String sql = buildSQLQueryOf(
				"INSERT INTO tb_m_book_author (author_id, book_id) ",
				"VALUES (?, ?) ",
				"RETURNING author_id ");
		return queryForObject(sql,
				(rs, rowNum) -> {
					Author author = new Author();
					author.setId(rs.getLong("id"));
					BookAuthor bookAuthor = new BookAuthor();
					bookAuthor.setAuthor(author);
					bookAuthor.setBook(request.getBook());
					return bookAuthor;
				},
				request.getAuthor().getId(),
				request.getBook().getId()
		);
	}

	@Override
	public BookAuthor get(BookAuthor request) throws Exception {
		String sql = buildSQLQueryOf(
				"SELECT * FROM tb_m_book_author ",
				"WHERE author_id = ? AND book_id = ? ");
		return queryForObject(sql,
				(rs, rowNum) -> {
					Author author = new Author();
					author.setId(rs.getLong("author_id"));
					Book book = new Book();
					book.setId(rs.getLong("book_id"));
					return new BookAuthor(author, book);
				},
				request.getAuthor().getId(),
				request.getBook().getId()
		);
	}

	@Override
	public BookAuthor update(BookAuthor request) throws Exception {
		return null;
	}

	@Override
	public BookAuthor delete(BookAuthor request) throws Exception {
		return null;
	}

}
