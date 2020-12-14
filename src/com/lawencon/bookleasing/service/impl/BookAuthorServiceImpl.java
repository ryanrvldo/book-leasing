package com.lawencon.bookleasing.service.impl;

import com.lawencon.bookleasing.dao.BookAuthorDao;
import com.lawencon.bookleasing.dao.impl.BookAuthorDaoImpl;
import com.lawencon.bookleasing.model.BookAuthor;
import com.lawencon.bookleasing.service.BookAuthorService;

/**
 * @author Rian Rivaldo Rumapea
 */
public class BookAuthorServiceImpl implements BookAuthorService {

  private BookAuthorDao dao = new BookAuthorDaoImpl();

  @Override
  public BookAuthor add(BookAuthor item) throws Exception {
    return dao.insert(item);
  }

  @Override
  public BookAuthor get(BookAuthor item) throws Exception {
    return dao.get(item);
  }

}
