package com.lawencon.bookleasing.dao.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.bookleasing.dao.CategoryDao;
import com.lawencon.bookleasing.entity.Book;
import com.lawencon.bookleasing.entity.Category;
import com.lawencon.bookleasing.repository.CategoryRepository;

/**
 * @author Rian Rivaldo
 */
@Repository("category-jpa")
public class CategoryDaoJpaImpl implements CategoryDao {

  private final CategoryRepository repository;

  @Autowired
  public CategoryDaoJpaImpl(CategoryRepository repository) {
	this.repository = repository;
  }

  @Override
  public Category findById(Long id) throws Exception {
	return repository.findById(id).get();
  }

  @Override
  public Category findByName(String name) throws Exception {
	return repository.findByName(name);
  }

  @Override
  public void insert(Category data) throws Exception {
	repository.save(data);
  }

  @Override
  public void update(Category data) throws Exception {
	repository.save(data);
  }

  @Override
  public void delete(Category data) throws Exception {
	repository.delete(data);
  }

  @Override
  public void deleteById(Long id) throws Exception {
	repository.deleteById(id);
  }

  @Override
  public List<Category> findAll() throws Exception {
	return repository.findAll();
  }

  @Override
  public List<Category> findAllByBook(Book book) throws Exception {
	return repository.findAllByBook(book.getId());
  }

}
