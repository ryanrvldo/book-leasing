package com.lawencon.bookleasing.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lawencon.bookleasing.dao.BookCategoryDao;
import com.lawencon.bookleasing.entity.BookCategory;
import com.lawencon.bookleasing.service.BookCategoryService;

/**
 * @author Rian Rivaldo
 */
@Service
public class BookCategoryServiceImpl implements BookCategoryService {

  private final BookCategoryDao dao;

  @Autowired
  public BookCategoryServiceImpl(@Qualifier("book-category-jpa") BookCategoryDao dao) {
	this.dao = dao;
  }

  @Override
  public void create(BookCategory data) throws Exception {
	this.dao.insert(data);
  }

  @Override
  public BookCategory get(BookCategory data) throws Exception {
	return null;
  }

  @Override
  public void update(BookCategory data) throws Exception {
	dao.update(data);
  }

  @Override
  public void delete(BookCategory data) throws Exception {
	dao.delete(data);
  }

  @Override
  public List<BookCategory> getAll() throws Exception {
	return dao.findAll();
  }

}
