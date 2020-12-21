package com.lawencon.bookleasing.dao.jdbc.basic;

import com.lawencon.bookleasing.dao.CustomerDao;
import com.lawencon.bookleasing.entity.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Rian Rivaldo Rumapea
 */
public class CustomerDaoImpl extends BaseDaoImpl implements CustomerDao {

	@Override
	public Customer insert(Customer request) throws Exception {
		String query = buildSQLQueryOf(
				"INSERT INTO tb_m_customer (profile_id) ",
				"VALUES (?) ",
				"RETURNING id");
		PreparedStatement statement = getConnection().prepareStatement(query);
		statement.setLong(1, request.getProfile().getId());

		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			Customer customer = new Customer();
			customer.setId(resultSet.getLong("id"));
			return customer;
		}
		return null;
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
