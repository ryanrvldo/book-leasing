package com.lawencon.bookleasing.dao.hql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.lawencon.bookleasing.error.DataIsNotExistsException;

/**
 * @author Rian Rivaldo Rumapea
 */
class BaseDaoImpl<T> {

  private final Class<T> resultClass;

  @PersistenceContext
  protected EntityManager entityManager;

  public BaseDaoImpl(Class<T> resultClass) {
	this.resultClass = resultClass;
  }

  protected T getSingleResultById(String query, Long id) {
	return getEntityManager().createQuery(query, resultClass)
	    .setParameter(1, id)
	    .getSingleResult();
  }

  protected T getSingleResultFromEntity(String query, Object... params) {
	TypedQuery<T> typedQuery = getEntityManager().createQuery(query, resultClass);
	setTypedQueryParams(typedQuery, params);
	return typedQuery.getSingleResult();
  }

  protected <R> R getCustomSingleResult(String query, Class<R> resultClass, Object... params) {
	TypedQuery<R> typedQuery = getEntityManager().createQuery(query, resultClass);
	setTypedQueryParams(typedQuery, params);
	return typedQuery.getSingleResult();
  }

  protected void insertData(T data) {
	getEntityManager().persist(data);
  }

  protected void mergeUpdateData(T data) {
	getEntityManager().merge(data);
  }

  protected void deleteData(T data) {
	getEntityManager().remove(data);
  }

  protected void deleteEntityById(String query, Long id) throws Exception {
	int deletedRow = getEntityManager().createQuery(query)
	    .setParameter(1, id)
	    .executeUpdate();
	if (deletedRow <= 0) {
	  throw new DataIsNotExistsException(id);
	}
  }

  protected void deleteDataCustom(String query, Object... params) throws Exception {
	Query objQuery = getEntityManager().createQuery(query);
	int paramLength = params.length;
	for (int i = 0; i < paramLength; i++) {
	  objQuery.setParameter(i + 1, params[i]);
	}
	int updatedRow = objQuery.executeUpdate();
	if (updatedRow <= 0) {
	  throw new Exception("No deleted row.");
	}
  }

  protected List<T> getAllData() {
	String hql = "FROM " + resultClass.getSimpleName();
	return getEntityManager()
	    .createQuery(hql, resultClass)
	    .getResultList();
  }

  private void setTypedQueryParams(TypedQuery<?> typedQuery, Object... params) {
	int paramLength = params.length;
	for (int i = 0; i < paramLength; i++) {
	  typedQuery.setParameter(i + 1, params[i]);
	}
  }

  public EntityManager getEntityManager() {
	return this.entityManager;
  }

}
