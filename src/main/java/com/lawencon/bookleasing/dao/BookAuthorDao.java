package com.lawencon.bookleasing.dao;

import com.lawencon.bookleasing.entity.BookAuthor;

/**
 * @author Rian Rivaldo Rumapea
 */
public interface BookAuthorDao extends BaseDao<BookAuthor> {

  BookAuthor findByBookAndAuthorId(Long bookId, Long authorId) throws Exception;

}
