package com.lawencon.bookleasing.dao;

import com.lawencon.bookleasing.entity.Customer;

/**
 * @author Rian Rivaldo Rumapea
 */
public interface CustomerDao extends BaseDao<Customer> {

  Customer findByEmail(String email) throws Exception;

}
