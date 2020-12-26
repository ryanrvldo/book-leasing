package com.lawencon.bookleasing.dao;

import com.lawencon.bookleasing.entity.Book;

/**
 * @author Rian Rivaldo Rumapea
 */
public interface BookDao extends BaseDao<Book> {

  Book findById(Long id) throws Exception;

  Book findByIsbn(String isbn) throws Exception;

  void deleteBookByIsbn(String isbn) throws Exception;

}
