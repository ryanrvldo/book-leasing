package com.lawencon.bookleasing.service.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.bookleasing.dao.CategoryDao;
import com.lawencon.bookleasing.entity.Book;
import com.lawencon.bookleasing.entity.BookCategory;
import com.lawencon.bookleasing.entity.Category;
import com.lawencon.bookleasing.error.DataAlreadyExistsException;
import com.lawencon.bookleasing.error.DataIsNotExistsException;
import com.lawencon.bookleasing.service.BookCategoryService;
import com.lawencon.bookleasing.service.CategoryService;

/**
 * @author Rian Rivaldo
 */
@Service
public class CategoryServiceImpl implements CategoryService {

  private final CategoryDao categoryDao;
  private final BookCategoryService bookCategoryService;

  @Autowired
  public CategoryServiceImpl(@Qualifier("category-jpa") CategoryDao categoryDao,
      BookCategoryService bookCategoryService) {
	this.categoryDao = categoryDao;
	this.bookCategoryService = bookCategoryService;
  }

  @Transactional
  @Override
  public void createBookCategory(Book book, List<Category> categoryList) throws Exception {
	for (Category category : categoryList) {
	  try {
		this.create(category);
	  } catch (DataAlreadyExistsException e) {
	  } finally {
		BookCategory bookCategory = new BookCategory();
		bookCategory.setBook(book);
		bookCategory.setCategory(category);
		bookCategoryService.create(bookCategory);
	  }
	}
  }

  @Transactional
  @Override
  public void create(Category data) throws Exception {
	try {
	  Category checkedCategory = getCategoryByName(data.getName());
	  if (checkedCategory != null) {
		throw new DataAlreadyExistsException(checkedCategory.getId());
	  }
	} catch (DataIsNotExistsException e) {
	  this.categoryDao.insert(data);
	}
  }

  @Override
  public Category getCategoryByName(String name) throws Exception {
	return Optional.ofNullable(validateGet(() -> categoryDao.findByName(name)))
	    .orElseThrow(() -> new DataIsNotExistsException(name));
  }

  @Override
  public void update(Category data) throws Exception {
	categoryDao.update(data);
  }

  @Override
  public void delete(Category data) throws Exception {
	categoryDao.delete(data);
  }

  @Override
  public List<Category> getAll() throws Exception {
	List<Category> categoryList = categoryDao.findAll();
	if (categoryList.isEmpty()) {
	  throw new NoResultException("Category list is empty.");
	}
	return categoryList;
  }

  @Override
  public List<Category> getAllCategoryByBook(Book book) throws Exception {
	return categoryDao.findAllByBook(book);
  }

}
