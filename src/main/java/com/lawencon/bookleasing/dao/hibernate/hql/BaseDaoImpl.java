package com.lawencon.bookleasing.dao.hibernate.hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * @author Rian Rivaldo Rumapea
 */
class BaseDaoImpl<T> {

	private SessionFactory sessionFactory;

	protected void insertData(T data) {
		getSession().persist(data);
	}

	protected void mergeUpdateData(T data) {
		getSession().merge(data);
	}

	protected void deleteData(T data) {
		getSession().remove(data);
	}

	protected T singleResultFromList(List<T> list) {
		return (list != null ? list.size() : 0) > 0 ? list.get(0) : null;
	}

	protected List<T> getAllData(Class<T> className) {
		String hql = "FROM " + className.getSimpleName();
		return getSession()
				.createQuery(hql, className)
				.list();
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

}
