package com.lawencon.bookleasing.repository.impl;

import com.lawencon.bookleasing.dao.LanguageDao;
import com.lawencon.bookleasing.entity.Language;
import com.lawencon.bookleasing.repository.LanguageRepository;

/**
 * @author Rian Rivaldo Rumapea
 */
public class LanguageRepositoryImpl implements LanguageRepository {

	private final LanguageDao dao;

	public LanguageRepositoryImpl(LanguageDao dao) {
		this.dao = dao;
	}

	@Override
	public void add(Language request) throws Exception {
		this.dao.insert(request);
	}

	@Override
	public Language get(Language request) throws Exception {
		return this.dao.get(request);
	}

}
