package com.lawencon.bookleasing.dao;

import java.util.List;

import com.lawencon.bookleasing.entity.Book;
import com.lawencon.bookleasing.entity.Category;

/**
 * @author Rian Rivaldo
 */
public interface CategoryDao extends BaseDao<Category> {

  Category findByName(String name) throws Exception;

  List<Category> findAllByBook(Book book) throws Exception;

}
