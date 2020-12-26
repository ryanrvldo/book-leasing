package com.lawencon.bookleasing.dao.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.bookleasing.dao.BookCategoryDao;
import com.lawencon.bookleasing.entity.BookCategory;
import com.lawencon.bookleasing.repository.BookCategoryRepository;

/**
 * @author Rian Rivaldo
 */
@Repository("book-category-jpa")
public class BookCategoryDaoJpaImpl implements BookCategoryDao {

  private final BookCategoryRepository repository;

  @Autowired
  public BookCategoryDaoJpaImpl(BookCategoryRepository repository) {
	this.repository = repository;
  }

  @Override
  public BookCategory findById(Long id) throws Exception {
	return repository.findById(id).get();
  }

  @Override
  public void insert(BookCategory data) throws Exception {
	repository.save(data);
  }

  @Override
  public void update(BookCategory data) throws Exception {
	repository.save(data);
  }

  @Override
  public void delete(BookCategory data) throws Exception {
	repository.delete(data);
  }

  @Override
  public void deleteById(Long id) throws Exception {
	repository.deleteById(id);
  }

  @Override
  public List<BookCategory> findAll() throws Exception {
	return repository.findAll();
  }

}
