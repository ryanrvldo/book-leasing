package com.lawencon.bookleasing.service.impl;

import com.lawencon.bookleasing.dao.CategoryDao;
import com.lawencon.bookleasing.dao.impl.CategoryDaoImpl;
import com.lawencon.bookleasing.model.Category;
import com.lawencon.bookleasing.service.CategoryService;

/**
 * @author Rian Rivaldo Rumapea
 */
public class CategoryServiceImpl implements CategoryService {

  private CategoryDao dao = new CategoryDaoImpl();

  @Override
  public Category add(Category item) throws Exception {
    return dao.insert(item);
  }

  @Override
  public Category get(Category item) throws Exception {
    return dao.get(item);
  }

}
