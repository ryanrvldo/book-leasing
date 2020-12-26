package com.lawencon.bookleasing.dao.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.bookleasing.dao.LanguageDao;
import com.lawencon.bookleasing.entity.Language;
import com.lawencon.bookleasing.repository.LanguageRepository;

/**
 * @author Rian Rivaldo
 */
@Repository("language-jpa")
public class LanguageDaoJpaImpl implements LanguageDao {

  private final LanguageRepository repository;

  @Autowired
  public LanguageDaoJpaImpl(LanguageRepository repository) {
	this.repository = repository;
  }

  @Override
  public Language findById(Long id) throws Exception {
	return repository.findById(id).get();
  }

  @Override
  public void insert(Language data) throws Exception {
	repository.save(data);
  }

  @Override
  public void update(Language data) throws Exception {
	repository.save(data);
  }

  @Override
  public void delete(Language data) throws Exception {
	repository.delete(data);
  }

  @Override
  public void deleteById(Long id) throws Exception {
	repository.deleteById(id);
  }

  @Override
  public List<Language> findAll() throws Exception {
	return repository.findAll();
  }

  @Override
  public Language findByCode(String code) throws Exception {
	return repository.findByCode(code);
  }

}
