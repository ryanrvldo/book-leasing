package com.lawencon.bookleasing.service.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.bookleasing.dao.AuthorDao;
import com.lawencon.bookleasing.entity.Author;
import com.lawencon.bookleasing.entity.Book;
import com.lawencon.bookleasing.entity.BookAuthor;
import com.lawencon.bookleasing.error.DataAlreadyExistsException;
import com.lawencon.bookleasing.error.DataIsNotExistsException;
import com.lawencon.bookleasing.service.AuthorService;
import com.lawencon.bookleasing.service.BookAuthorService;

/**
 * @author Rian Rivaldo
 */
@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {

  private final AuthorDao authorDao;
  private final BookAuthorService bookAuthorService;

  @Autowired
  public AuthorServiceImpl(@Qualifier("author-hql") AuthorDao dao, BookAuthorService bookAuthorService) {
	this.authorDao = dao;
	this.bookAuthorService = bookAuthorService;
  }

  @Transactional
  @Override
  public void createBookAuthor(Book book, List<Author> authorList) throws Exception {
	for (Author author : authorList) {
	  try {
		this.create(author);
	  } catch (DataAlreadyExistsException e) {
	  } finally {
		BookAuthor bookAuthor = new BookAuthor();
		bookAuthor.setBook(book);
		bookAuthor.setAuthor(author);
		bookAuthorService.create(bookAuthor);
	  }
	}
  }

  @Transactional
  @Override
  public void create(Author data) throws Exception {
	try {
	  Author checkedAuthor = getAuthorByFirstAndLastName(data.getFirstName(), data.getLastName());
	  if (checkedAuthor != null) {
		data = checkedAuthor;
		throw new DataAlreadyExistsException(checkedAuthor.getId());
	  }
	} catch (DataIsNotExistsException e) {
	  this.authorDao.insert(data);
	}
  }

  @Override
  public Author getAuthorById(Long id) throws Exception {
	return Optional.ofNullable(validateGet(() -> authorDao.findById(id)))
	    .orElseThrow(() -> new DataIsNotExistsException(id));
  }

  @Override
  public Author getAuthorByFirstAndLastName(String firstName, String lastName) throws Exception {
	return Optional.ofNullable(validateGet(() -> this.authorDao.getByFirstAndLastName(firstName, lastName)))
	    .orElseThrow(() -> new DataIsNotExistsException(firstName));
  }

  @Override
  public void update(Author data) throws Exception {
	authorDao.update(data);
  }

  @Override
  public void delete(Author data) throws Exception {
	authorDao.delete(data);
  }

  @Override
  public void deleteById(Long id) throws Exception {
	authorDao.deleteById(id);
  }

  @Override
  public List<Author> getAll() throws Exception {
	List<Author> authorList = authorDao.findAll();
	if (authorList.isEmpty()) {
	  throw new NoResultException("Author list is empty.");
	}
	return authorList;
  }

  @Override
  public List<Author> getAllAuthorByBook(Book book) throws Exception {
	return authorDao.findAllByBook(book);
  }

}
