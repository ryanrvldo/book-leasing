package com.lawencon.bookleasing.service.impl;

import com.lawencon.bookleasing.dao.BookDao;
import com.lawencon.bookleasing.dao.impl.BookDaoImpl;
import com.lawencon.bookleasing.model.Book;
import com.lawencon.bookleasing.service.BookService;

/**
 * @author Rian Rivaldo Rumapea
 */
public class BookServiceImpl implements BookService {

  private BookDao dao = new BookDaoImpl();

  @Override
  public Book add(Book newBook) throws Exception {
    return dao.insert(newBook);
  }

  @Override
  public Book get(Book item) throws Exception {
    throw new IllegalAccessException("Not implemented yet. Under development.");
  }

}
