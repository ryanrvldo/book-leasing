package com.lawencon.bookleasing.dao.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.bookleasing.dao.BookAuthorDao;
import com.lawencon.bookleasing.entity.BookAuthor;
import com.lawencon.bookleasing.repository.BookAuthorRepository;

/**
 * @author Rian Rivaldo
 */
@Repository("book-author-jpa")
public class BookAuthorDaoJpaImpl implements BookAuthorDao {

  private final BookAuthorRepository repository;

  @Autowired
  public BookAuthorDaoJpaImpl(BookAuthorRepository repository) {
	this.repository = repository;
  }

  @Override
  public BookAuthor findById(Long id) throws Exception {
	return repository.findById(id).get();
  }

  @Override
  public BookAuthor findByBookAndAuthorId(Long bookId, Long authorId) throws Exception {
	return repository.findByBookId(bookId);
  }

  @Override
  public void insert(BookAuthor data) throws Exception {
	repository.save(data);
  }

  @Override
  public void update(BookAuthor data) throws Exception {
	repository.save(data);
  }

  @Override
  public void delete(BookAuthor data) throws Exception {
	repository.delete(data);
  }

  @Override
  public void deleteById(Long id) throws Exception {
	repository.deleteById(id);
  }

  @Override
  public List<BookAuthor> findAll() throws Exception {
	return repository.findAll();
  }

}
