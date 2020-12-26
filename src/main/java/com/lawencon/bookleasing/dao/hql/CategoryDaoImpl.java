package com.lawencon.bookleasing.dao.hql;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.bookleasing.dao.CategoryDao;
import com.lawencon.bookleasing.entity.Book;
import com.lawencon.bookleasing.entity.Category;

/**
 * @author Rian Rivaldo
 */
@Repository("category-hql")
public class CategoryDaoImpl extends BaseDaoImpl<Category> implements CategoryDao {

  public CategoryDaoImpl() {
	super(Category.class);
  }

  @Override
  public Category findById(Long id) throws Exception {
	String hql = buildQueryOf(
	    "FROM Category ",
	    "WHERE id = ?1 ");
	return getSingleResultById(hql, id);
  }

  @Override
  public Category findByName(String name) throws Exception {
	String hql = buildQueryOf(
	    "FROM Category ",
	    "WHERE lower(name) = lower(?1) ");
	return getSingleResultFromEntity(hql, name);
  }

  @Override
  public void insert(Category data) throws Exception {
	insertData(data);
  }

  @Override
  public void update(Category data) throws Exception {
	mergeUpdateData(data);
  }

  @Override
  public void deleteById(Long id) throws Exception {
	String hql = buildQueryOf(
	    "DELETE FROM Category ",
	    "WHERE id = ?1 ");
	deleteEntityById(hql, id);
  }

  @Override
  public void delete(Category data) throws Exception {
	deleteData(data);
  }

  @Override
  public List<Category> findAll() throws Exception {
	return getAllData();
  }

  @Override
  public List<Category> findAllByBook(Book book) throws Exception {
	String hql = buildQueryOf("SELECT c FROM Category c ",
	    "INNER JOIN BookCategory bc ON bc.category.id = c.id ",
	    "WHERE bc.book.id = ?1 ");
	List<Category> categoryList = getEntityManager()
	    .createQuery(hql, Category.class)
	    .setParameter(1, book.getId())
	    .getResultList();
	return categoryList;
  }

}
