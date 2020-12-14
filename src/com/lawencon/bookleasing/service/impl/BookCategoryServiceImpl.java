package com.lawencon.bookleasing.service.impl;

import com.lawencon.bookleasing.dao.BookCategoryDao;
import com.lawencon.bookleasing.dao.impl.BookCategoryDaoImpl;
import com.lawencon.bookleasing.model.BookCategory;
import com.lawencon.bookleasing.service.BookCategoryService;

/**
 * @author Rian Rivaldo Rumapea
 */
public class BookCategoryServiceImpl implements BookCategoryService {

  private BookCategoryDao dao = new BookCategoryDaoImpl();

  @Override
  public BookCategory add(BookCategory item) throws Exception {
    return dao.insert(item);
  }

  @Override
  public BookCategory get(BookCategory item) throws Exception {
    return dao.get(item);
  }

}
