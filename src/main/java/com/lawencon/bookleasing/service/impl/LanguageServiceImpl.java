package com.lawencon.bookleasing.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lawencon.bookleasing.dao.LanguageDao;
import com.lawencon.bookleasing.entity.Language;
import com.lawencon.bookleasing.service.LanguageService;

/**
 * @author Rian Rivaldo Rumapea
 */
@Service
public class LanguageServiceImpl implements LanguageService {

  private final LanguageDao dao;

  @Autowired
  public LanguageServiceImpl(@Qualifier("language-hql") LanguageDao dao) {
	this.dao = dao;
  }

  @Override
  public void create(Language data) throws Exception {
	this.dao.insert(data);
  }

  @Override
  public Language getLanguageByCode(String code) throws Exception {
	return validateGet(() -> this.dao.findByCode(code));
  }

  @Override
  public void update(Language data) throws Exception {
	dao.update(data);
  }

  @Override
  public void delete(Language data) throws Exception {
	dao.delete(data);
  }

  @Override
  public List<Language> getAll() throws Exception {
	return dao.findAll();
  }

}
