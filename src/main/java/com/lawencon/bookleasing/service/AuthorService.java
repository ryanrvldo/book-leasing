package com.lawencon.bookleasing.service;

import java.util.List;

import com.lawencon.bookleasing.entity.Author;
import com.lawencon.bookleasing.entity.Book;

/**
 * @author Rian Rivaldo Rumapea
 */
public interface AuthorService extends BaseService<Author> {

  Author getAuthorById(Long id) throws Exception;

  Author getAuthorByFirstAndLastName(String firstName, String lastName) throws Exception;

  List<Author> getAllAuthorByBook(Book book) throws Exception;

  void deleteById(Long id) throws Exception;

  void createBookAuthor(Book book, List<Author> authorList) throws Exception;

}
