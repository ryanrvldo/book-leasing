package com.lawencon.bookleasing.dao.hql;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.bookleasing.dao.BookCategoryDao;
import com.lawencon.bookleasing.entity.BookCategory;

/**
 * @author Rian Rivaldo
 */
@Repository("book-category-hql")
public class BookCategoryDaoImpl extends BaseDaoImpl<BookCategory> implements BookCategoryDao {

  public BookCategoryDaoImpl() {
	super(BookCategory.class);
  }

  @Override
  public BookCategory findById(Long id) throws Exception {
	String hql = buildQueryOf(
	    "FROM BookCategory ",
	    "WHERE id = ?1 ");
	return getSingleResultById(hql, id);
  }

  @Override
  public void insert(BookCategory data) throws Exception {
	insertData(data);
  }

  @Override
  public void update(BookCategory data) throws Exception {
	mergeUpdateData(data);
  }

  @Override
  public void deleteById(Long id) throws Exception {
	String hql = buildQueryOf(
	    "FROM BookCategory ",
	    "WHERE id = ?1 ");
	deleteEntityById(hql, id);
  }

  @Override
  public void delete(BookCategory data) throws Exception {
	deleteData(data);
  }

  @Override
  public List<BookCategory> findAll() throws Exception {
	return getAllData();
  }

}
