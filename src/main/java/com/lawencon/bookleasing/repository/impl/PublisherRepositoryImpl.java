package com.lawencon.bookleasing.repository.impl;

import com.lawencon.bookleasing.dao.PublisherDao;
import com.lawencon.bookleasing.entity.Publisher;
import com.lawencon.bookleasing.repository.PublisherRepository;

/**
 * @author Rian Rivaldo Rumapea
 */
public class PublisherRepositoryImpl implements PublisherRepository {

	private final PublisherDao dao;

	public PublisherRepositoryImpl(PublisherDao dao) {
		this.dao = dao;
	}

	@Override
	public void add(Publisher request) throws Exception {
		this.dao.insert(request);
	}

	@Override
	public Publisher get(Publisher request) throws Exception {
		return this.dao.get(request);
	}

}
