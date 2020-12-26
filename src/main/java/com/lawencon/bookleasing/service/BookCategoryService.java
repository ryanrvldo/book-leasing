package com.lawencon.bookleasing.service;

import com.lawencon.bookleasing.entity.BookCategory;

/**
 * @author Rian Rivaldo Rumapea
 */
public interface BookCategoryService extends BaseService<BookCategory> {

  BookCategory get(BookCategory data) throws Exception;

}
