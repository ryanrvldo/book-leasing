package com.lawencon.bookleasing.dao.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.bookleasing.dao.BookDao;
import com.lawencon.bookleasing.entity.Book;
import com.lawencon.bookleasing.repository.BookRepository;

/**
 * @author Rian Rivaldo
 */
@Repository("book-jpa")
public class BookDaoJpaImpl implements BookDao {

  private final BookRepository bookRepository;

  @Autowired
  public BookDaoJpaImpl(BookRepository bookRepository) {
	this.bookRepository = bookRepository;
  }

  @Override
  public void insert(Book data) throws Exception {
	this.bookRepository.save(data);
  }

  @Override
  public Book findById(Long id) throws Exception {
	return this.bookRepository.findByBookId(id);
  }

  @Override
  public Book findByIsbn(String isbn) throws Exception {
	return this.bookRepository.findByIsbn(isbn);
  }

  @Override
  public void update(Book data) throws Exception {
	this.bookRepository.save(data);
  }

  @Override
  public void delete(Book data) throws Exception {
	this.bookRepository.delete(data);
  }

  @Override
  public void deleteById(Long id) throws Exception {
	this.bookRepository.deleteById(id);
  }

  @Override
  public void deleteBookByIsbn(String isbn) throws Exception {
	this.bookRepository.deleteByIsbn(isbn);
  }

  @Override
  public List<Book> findAll() throws Exception {
	return this.bookRepository.findAll();
  }

}
