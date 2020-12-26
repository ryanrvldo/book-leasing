package com.lawencon.bookleasing.dao.hql;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.bookleasing.dao.BookAuthorDao;
import com.lawencon.bookleasing.entity.BookAuthor;

/**
 * @author Rian Rivaldo
 */
@Repository("book-author-hql")
public class BookAuthorDaoImpl extends BaseDaoImpl<BookAuthor> implements BookAuthorDao {

  public BookAuthorDaoImpl() {
	super(BookAuthor.class);
  }

  @Override
  public BookAuthor findById(Long id) throws Exception {
	String hql = buildQueryOf(
	    "FROM BookAuthor ",
	    "WHERE id = ?1 ");
	return getSingleResultById(hql, id);
  }

  @Override
  public BookAuthor findByBookAndAuthorId(Long bookId, Long authorId) throws Exception {
	String hql = buildQueryOf(
	    "FROM BookAuthor ",
	    "WHERE book.id = ?1 AND author.id = ?2 ");
	return getSingleResultFromEntity(hql,
	    bookId,
	    authorId);
  }

  @Override
  public void insert(BookAuthor data) throws Exception {
	insertData(data);
  }

  @Override
  public void update(BookAuthor data) throws Exception {
	mergeUpdateData(data);
  }

  @Override
  public void deleteById(Long id) throws Exception {
	String hql = buildQueryOf(
	    "DELETE FROM BookAuthor ",
	    "WHERE id = ?1 ");
	deleteEntityById(hql, id);
  }

  @Override
  public void delete(BookAuthor data) throws Exception {
	deleteData(data);
  }

  @Override
  public List<BookAuthor> findAll() throws Exception {
	return getAllData();
  }

}
