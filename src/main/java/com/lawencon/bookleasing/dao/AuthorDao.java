package com.lawencon.bookleasing.dao;

import java.util.List;

import com.lawencon.bookleasing.entity.Author;
import com.lawencon.bookleasing.entity.Book;

/**
 * @author Rian Rivaldo Rumapea
 */
public interface AuthorDao extends BaseDao<Author> {

  Author getByFirstAndLastName(String firstName, String lastName) throws Exception;

  List<Author> findAllByBook(Book book) throws Exception;

}
