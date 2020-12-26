package com.lawencon.bookleasing.service;

import com.lawencon.bookleasing.entity.Book;
import com.lawencon.bookleasing.model.BookDetails;

/**
 * @author Rian Rivaldo Rumapea
 */
public interface BookService extends BaseService<Book> {

  void createBookWithDetails(BookDetails book) throws Exception;

  BookDetails getBookByIsbn(String isbn) throws Exception;

  BookDetails getBookById(Long id) throws Exception;

}
