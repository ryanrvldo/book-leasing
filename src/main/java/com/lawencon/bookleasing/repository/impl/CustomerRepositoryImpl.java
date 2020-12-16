package com.lawencon.bookleasing.repository.impl;

import com.lawencon.bookleasing.dao.CustomerDao;
import com.lawencon.bookleasing.entity.Customer;
import com.lawencon.bookleasing.repository.CustomerRepository;

/**
 * @author Rian Rivaldo Rumapea
 */
public class CustomerRepositoryImpl implements CustomerRepository {

	private final CustomerDao dao;

	public CustomerRepositoryImpl(CustomerDao dao) {
		this.dao = dao;
	}

	@Override
	public Customer add(Customer request) throws Exception {
		return dao.insert(request);
	}

	@Override
	public Customer get(Customer request) throws Exception {
		throw new IllegalAccessException("Not implemented yet. Under development.");
	}

}
