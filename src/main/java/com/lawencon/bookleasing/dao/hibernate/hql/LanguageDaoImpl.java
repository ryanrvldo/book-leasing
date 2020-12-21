package com.lawencon.bookleasing.dao.hibernate.hql;

import com.lawencon.bookleasing.dao.LanguageDao;
import com.lawencon.bookleasing.entity.Language;

import java.util.List;

/**
 * @author Rian Rivaldo Rumapea
 */
public class LanguageDaoImpl extends BaseDaoImpl<Language> implements LanguageDao {

	@Override
	public void insert(Language request) throws Exception {
		insertData(request);
	}

	@Override
	public Language get(Language request) throws Exception {
		List<Language> results = getSession()
				.createQuery("FROM Language WHERE lower(code) = lower(?1) ", Language.class)
				.setParameter(1, request.getCode())
				.list();
		return singleResultFromList(results);
	}

	@Override
	public void update(Language request) throws Exception {
		mergeUpdateData(request);
	}

	@Override
	public void delete(Language request) throws Exception {
		deleteData(request);
	}

	@Override
	public List<Language> getAll() throws Exception {
		return getAllData(Language.class);
	}
}
