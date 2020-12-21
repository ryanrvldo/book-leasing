package com.lawencon.bookleasing.dao.jdbc.template;

import com.lawencon.bookleasing.dao.RentalHeaderDao;
import com.lawencon.bookleasing.entity.RentalHeader;

/**
 * @author Rian Rivaldo Rumapea
 */
public class RentalHeaderDaoImpl extends BaseDaoImpl implements RentalHeaderDao {

	@Override
	public RentalHeader insert(RentalHeader request) throws Exception {
		String sql = buildSQLQueryOf(
				"INSERT INTO tb_r_hdr_rental (receipt, customer_id, user_id) ",
				"VALUES (?, ?, ?) ",
				"RETURNING id");
		return queryForObject(sql,
				(rs, rowNum) -> {
					RentalHeader resultHeader = new RentalHeader();
					resultHeader.setId(rs.getLong("id"));
					return resultHeader;
				},
				request.getReceipt(),
				request.getCustomer().getId(),
				request.getUser().getId()
		);
	}

	@Override
	public RentalHeader get(RentalHeader request) throws Exception {
		String sql = buildSQLQueryOf(
				"SELECT id, receipt ",
				"FROM tb_r_hdr_rental ",
				"WHERE receipt = ? ");
		return queryForObject(sql,
				(rs, rowNum) -> {
					RentalHeader resultHeader = new RentalHeader();
					resultHeader.setId(rs.getLong("id"));
					resultHeader.setReceipt(rs.getString("receipt"));
					return resultHeader;
				},
				request.getReceipt()
		);
	}

	@Override
	public RentalHeader update(RentalHeader request) throws Exception {
		return null;
	}

	@Override
	public RentalHeader delete(RentalHeader request) throws Exception {
		return null;
	}

}
