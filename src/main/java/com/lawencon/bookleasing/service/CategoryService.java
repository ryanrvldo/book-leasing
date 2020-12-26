package com.lawencon.bookleasing.service;

import java.util.List;

import com.lawencon.bookleasing.entity.Book;
import com.lawencon.bookleasing.entity.Category;

/**
 * @author Rian Rivaldo Rumapea
 */
public interface CategoryService extends BaseService<Category> {

  Category getCategoryByName(String name) throws Exception;

  List<Category> getAllCategoryByBook(Book book) throws Exception;

  void createBookCategory(Book book, List<Category> categoryList) throws Exception;

}
