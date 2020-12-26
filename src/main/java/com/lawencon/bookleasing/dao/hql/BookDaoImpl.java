package com.lawencon.bookleasing.dao.hql;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.lawencon.bookleasing.dao.BookDao;
import com.lawencon.bookleasing.entity.Book;
import com.lawencon.bookleasing.entity.Language;
import com.lawencon.bookleasing.entity.Publisher;

/**
 * @author Rian Rivaldo
 */
@Repository("book-hql")
public class BookDaoImpl extends BaseDaoImpl<Book> implements BookDao {

  public BookDaoImpl() {
	super(Book.class);
  }

  @Override
  public void insert(Book data) throws Exception {
	insertData(data);
  }

  @Override
  public Book findById(Long id) throws Exception {
	return findBookByParam("WHERE b.id = ?1 ", id);
  }

  @Override
  public Book findByIsbn(String isbn) throws Exception {
	return findBookByParam("WHERE b.isbn = ?1 ", isbn);
  }

  @Override
  public void update(Book data) throws Exception {
	mergeUpdateData(data);
  }

  @Override
  public void delete(Book data) throws Exception {
	deleteData(data);
  }

  @Override
  public void deleteById(Long id) throws Exception {
	String hql = buildQueryOf(
	    "DELETE FROM Book ",
	    "WHERE id = ?1 ");
	deleteDataCustom(hql, id);
  }

  @Override
  public void deleteBookByIsbn(String isbn) throws Exception {
	String hql = buildQueryOf(
	    "DELETE FROM Book ",
	    "WHERE isbn = ?1 ");
	deleteDataCustom(hql, isbn);
  }

  @Override
  public List<Book> findAll() throws Exception {
	return getAllData();
  }

  private Book findBookByParam(String whereQuery, Object... params) {
	String hql = buildQueryOf("FROM Book b ",
	    "INNER JOIN b.language ",
	    "INNER JOIN b.publisher ",
	    whereQuery);
	TypedQuery<Object[]> typedQuery = getEntityManager().createQuery(hql, Object[].class);
	int paramsLength = params.length;
	for (int i = 0; i < paramsLength; i++) {
	  typedQuery.setParameter(i + 1, params[i]);
	}

	Object[] objArr = typedQuery.getSingleResult();
	Book book = (Book) objArr[0];
	book.setLanguage((Language) objArr[1]);
	book.setPublisher((Publisher) objArr[2]);
	return book;
  }

}
