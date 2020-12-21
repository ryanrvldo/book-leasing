package com.lawencon.bookleasing.dao.hibernate.hql;

import com.lawencon.bookleasing.dao.CustomerDao;
import com.lawencon.bookleasing.entity.Customer;

import java.util.List;

/**
 * @author Rian Rivaldo Rumapea
 */
public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao {

	@Override
	public void insert(Customer request) throws Exception {
		insertData(request);
	}

	@Override
	public Customer get(Customer request) throws Exception {
		return null;
	}

	@Override
	public void update(Customer request) throws Exception {
		mergeUpdateData(request);
	}

	@Override
	public void delete(Customer request) throws Exception {
		deleteData(request);
	}

	@Override
	public List<Customer> getAll() throws Exception {
		return getAllData(Customer.class);
	}

}
