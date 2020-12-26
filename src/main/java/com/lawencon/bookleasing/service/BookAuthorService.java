package com.lawencon.bookleasing.service;

import com.lawencon.bookleasing.entity.BookAuthor;

/**
 * @author Rian Rivaldo Rumapea
 */
public interface BookAuthorService extends BaseService<BookAuthor> {

  BookAuthor getByBookAndAuthorId(BookAuthor data) throws Exception;

}
