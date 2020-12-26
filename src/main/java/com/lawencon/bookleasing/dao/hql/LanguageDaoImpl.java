package com.lawencon.bookleasing.dao.hql;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.bookleasing.dao.LanguageDao;
import com.lawencon.bookleasing.entity.Language;

/**
 * @author Rian Rivaldo
 */
@Repository("language-hql")
public class LanguageDaoImpl extends BaseDaoImpl<Language> implements LanguageDao {

  public LanguageDaoImpl() {
	super(Language.class);
  }

  @Override
  public Language findById(Long id) throws Exception {
	String hql = buildQueryOf(
	    "FROM Language ",
	    "WHERE id = ?1 ");
	return getSingleResultFromEntity(hql, id);
  }

  @Override
  public Language findByCode(String code) throws Exception {
	String hql = buildQueryOf(
	    "FROM Language ",
	    "WHERE lower(code) = lower(?1) ");
	return getSingleResultFromEntity(hql, code);
  }

  @Override
  public void insert(Language data) throws Exception {
	insertData(data);
  }

  @Override
  public void update(Language data) throws Exception {
	mergeUpdateData(data);
  }

  @Override
  public void deleteById(Long id) throws Exception {
	String hql = buildQueryOf(
	    "FROM Language ",
	    "WHERE lower(code) = lower(?1) ");
	deleteEntityById(hql, id);
  }

  @Override
  public void delete(Language data) throws Exception {
	deleteData(data);
  }

  @Override
  public List<Language> findAll() throws Exception {
	return getAllData();
  }

}
