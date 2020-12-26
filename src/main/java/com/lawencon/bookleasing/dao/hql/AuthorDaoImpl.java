package com.lawencon.bookleasing.dao.hql;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.bookleasing.dao.AuthorDao;
import com.lawencon.bookleasing.entity.Author;
import com.lawencon.bookleasing.entity.Book;

/**
 * @author Rian Rivaldo
 */
@Repository("author-hql")
public class AuthorDaoImpl extends BaseDaoImpl<Author> implements AuthorDao {

  public AuthorDaoImpl() {
	super(Author.class);
  }

  @Override
  public Author findById(Long id) throws Exception {
	String hql = buildQueryOf(
	    "FROM Author ",
	    "WHERE id = ?1 ");
	return getSingleResultById(hql, id);
  }

  @Override
  public Author getByFirstAndLastName(String firstName, String lastName) throws Exception {
	String hql = buildQueryOf(
	    "FROM Author ",
	    "WHERE lower(firstName) = lower(?1) and lower(lastName) = lower(?2) ");
	return getSingleResultFromEntity(hql,
	    firstName,
	    lastName);
  }

  @Override
  public void insert(Author data) throws Exception {
	insertData(data);
  }

  @Override
  public void update(Author data) throws Exception {
	mergeUpdateData(data);
  }

  @Override
  public void delete(Author data) throws Exception {
	deleteData(data);
  }

  @Transactional
  @Override
  public void deleteById(Long id) throws Exception {
	String hql = buildQueryOf(
	    "DELETE FROM Author ",
	    "WHERE id = ?1 ");
	deleteEntityById(hql, id);
  }

  @Override
  public List<Author> findAll() throws Exception {
	return getAllData();
  }

  @Override
  public List<Author> findAllByBook(Book book) throws Exception {
	String hql = buildQueryOf("SELECT a FROM Author a ",
	    "INNER JOIN BookAuthor ba ON ba.author.id = a.id ",
	    "WHERE ba.book.id = ?1 ");
	List<Author> authorList = getEntityManager()
	    .createQuery(hql, Author.class)
	    .setParameter(1, book.getId())
	    .getResultList();
	return authorList;
  }

}
