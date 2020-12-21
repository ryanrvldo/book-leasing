package com.lawencon.bookleasing.dao;

import com.lawencon.bookleasing.entity.BookCategory;

import java.util.List;

/**
 * @author Rian Rivaldo Rumapea
 */
public interface BookCategoryDao extends BaseDao<BookCategory> {

	List<BookCategory> getAll() throws Exception;

}
