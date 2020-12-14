package com.lawencon.bookleasing.service.impl;

import com.lawencon.bookleasing.dao.AuthorDao;
import com.lawencon.bookleasing.dao.impl.AuthorDaoImpl;
import com.lawencon.bookleasing.model.Author;
import com.lawencon.bookleasing.service.AuthorService;

/**
 * @author Rian Rivaldo Rumapea
 */
public class AuthorServiceImpl implements AuthorService {

  private AuthorDao dao = new AuthorDaoImpl();

  @Override
  public Author add(Author item) throws Exception {
    return dao.insert(item);
  }

  @Override
  public Author get(Author item) throws Exception {
    return dao.get(item);
  }

}
