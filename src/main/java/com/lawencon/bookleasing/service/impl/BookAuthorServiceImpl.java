package com.lawencon.bookleasing.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lawencon.bookleasing.dao.BookAuthorDao;
import com.lawencon.bookleasing.entity.BookAuthor;
import com.lawencon.bookleasing.service.BookAuthorService;

/**
 * @author Rian Rivaldo Rumapea
 */
@Service
public class BookAuthorServiceImpl implements BookAuthorService {

  private final BookAuthorDao dao;

  @Autowired
  public BookAuthorServiceImpl(@Qualifier("book-author-jpa") BookAuthorDao dao) {
	this.dao = dao;
  }

  @Override
  public void create(BookAuthor data) throws Exception {
	this.dao.insert(data);
  }

  @Override
  public BookAuthor getByBookAndAuthorId(BookAuthor data) throws Exception {
	return validateGet(() -> this.dao.findByBookAndAuthorId(data.getBook().getId(), data.getAuthor().getId()));
  }

  @Override
  public void update(BookAuthor data) throws Exception {
	dao.update(data);
  }

  @Override
  public void delete(BookAuthor data) throws Exception {
	dao.delete(data);
  }

  @Override
  public List<BookAuthor> getAll() throws Exception {
	return dao.findAll();
  }

}
