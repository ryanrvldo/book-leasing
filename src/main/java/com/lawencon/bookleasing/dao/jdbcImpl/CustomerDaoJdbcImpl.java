package com.lawencon.bookleasing.dao.jdbcImpl;

import com.lawencon.bookleasing.dao.CustomerDao;
import com.lawencon.bookleasing.entity.Customer;

/**
 * @author Rian Rivaldo Rumapea
 */
public class CustomerDaoJdbcImpl extends BaseDaoJdbcImpl implements CustomerDao {

	@Override
	public Customer insert(Customer request) throws Exception {
		String sql = buildSQLQueryOf(
				"INSERT INTO tb_m_customer (profile_id) ",
				"VALUES (?) ",
				"RETURNING id");
		return queryForObject(sql,
				(rs, rowNum) -> {
					Customer customer = new Customer();
					customer.setId(rs.getLong("id"));
					return customer;
				},
				request.getProfile().getId()
		);
	}

	@Override
	public Customer get(Customer request) throws Exception {
		return null;
	}

	@Override
	public Customer update(Customer request) throws Exception {
		return null;
	}

	@Override
	public Customer delete(Customer request) throws Exception {
		return null;
	}

}
