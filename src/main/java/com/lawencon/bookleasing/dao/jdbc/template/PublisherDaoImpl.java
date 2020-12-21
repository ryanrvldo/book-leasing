package com.lawencon.bookleasing.dao.jdbc.template;

import com.lawencon.bookleasing.dao.PublisherDao;
import com.lawencon.bookleasing.entity.Publisher;

/**
 * @author Rian Rivaldo Rumapea
 */
public class PublisherDaoImpl extends BaseDaoImpl implements PublisherDao {

	@Override
	public Publisher insert(Publisher request) throws Exception {
		String sql = buildSQLQueryOf(
				"INSERT INTO tb_m_publisher(publisher_name, code, city) ",
				"VALUES (?, ?, ?) ",
				"RETURNING id ");
		return queryForObject(sql,
				(rs, rowNum) -> {
					Publisher resultPublisher = new Publisher();
					resultPublisher.setId(rs.getLong("id"));
					return resultPublisher;
				},
				request.getName(),
				request.getCode(),
				request.getCity()
		);
	}

	@Override
	public Publisher get(Publisher request) throws Exception {
		String sql = buildSQLQueryOf(
				"SELECT id, code ",
				"FROM tb_m_publisher ",
				"WHERE lower(code) = lower(?) ");
		return queryForObject(sql,
				(rs, rowNum) -> {
					Publisher resultPublisher = new Publisher();
					resultPublisher.setId(rs.getLong("id"));
					resultPublisher.setCode(rs.getString("code"));
					return resultPublisher;
				},
				request.getCode()
		);
	}

	@Override
	public Publisher update(Publisher request) throws Exception {
		return null;
	}

	@Override
	public Publisher delete(Publisher request) throws Exception {
		return null;
	}

}
