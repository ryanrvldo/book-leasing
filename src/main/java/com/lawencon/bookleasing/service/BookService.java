package com.lawencon.bookleasing.service;

import com.lawencon.bookleasing.entity.*;

/**
 * @author Rian Rivaldo Rumapea
 */
public interface BookService {

  Publisher checkBookPublisher(Publisher publisher) throws Exception;

  Publisher addNewPublisher(Publisher newPublisher) throws Exception;

  Language checkLanguage(Language language) throws Exception;

  Language addNewLanguage(Language language) throws Exception;

  Author checkAuthor(Author author) throws Exception;

  Author addNewAuthor(Author author) throws Exception;

  Category checkCategory(Category category) throws Exception;

  Category addNewCategory(Category category) throws Exception;

  void addBookAuthor(Author author, Book book) throws Exception;

  void addBookCategory(Category category, Book book) throws Exception;

  Book addNewBook(Book newBook) throws Exception;

}
