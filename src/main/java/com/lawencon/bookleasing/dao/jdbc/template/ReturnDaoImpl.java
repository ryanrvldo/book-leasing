package com.lawencon.bookleasing.dao.jdbc.template;

import com.lawencon.bookleasing.dao.ReturnDao;
import com.lawencon.bookleasing.entity.RentalHeader;
import com.lawencon.bookleasing.entity.Return;

/**
 * @author Rian Rivaldo Rumapea
 */
public class ReturnDaoImpl extends BaseDaoImpl implements ReturnDao {

	@Override
	public Double getRentalCost(String receiptNumber) throws Exception {
		String sql = buildSQLQueryOf(
				"SELECT sum(dtl.total_price) AS rental_cost ",
				"FROM tb_r_return AS r ",
				"RIGHT JOIN tb_r_hdr_rental AS hdr ON hdr.id = r.rental_hdr_id ",
				"RIGHT JOIN tb_r_dtl_rental AS dtl ON dtl.rental_hdr_id = hdr.id ",
				"GROUP BY hdr.id, hdr.receipt ",
				"HAVING hdr.receipt = ? ;");
		return queryForObject(sql,
				(rs, rowNum) -> rs.getDouble("rental_cost"),
				receiptNumber
		);
	}

	@Override
	public Return insert(Return request) throws Exception {
		String sql = buildSQLQueryOf(
				"INSERT INTO tb_r_return (rental_hdr_id, returned_at, user_id, total_price) ",
				"VALUES (?, ?, ?, ?) ",
				"RETURNING id");
		return queryForObject(sql,
				(rs, rowNum) -> {
					Return resultReturn = new Return();
					resultReturn.setId(rs.getLong("id"));
					return resultReturn;
				},
				request.getRentalHeader().getId(),
				request.getReturnedAt(),
				request.getUser().getId(),
				request.getTotalPrice()
		);
	}

	@Override
	public Return get(Return request) throws Exception {
		String sql = buildSQLQueryOf(
				"SELECT hdr.id, hdr.receipt ",
				"FROM tb_r_return r ",
				"RIGHT JOIN tb_r_hdr_rental hdr ON hdr.id = r.rental_hdr_id ",
				"WHERE COALESCE(r.id, 0) = 0 AND hdr.id = ?;");
		return queryForObject(sql,
				(rs, rowNum) -> {
					RentalHeader header = new RentalHeader();
					header.setId(rs.getLong("id"));
					header.setReceipt(rs.getString("receipt"));

					Return result = new Return();
					result.setRentalHeader(header);
					return result;
				},
				request.getRentalHeader().getId()
		);
	}

	@Override
	public Return update(Return request) throws Exception {
		return null;
	}

	@Override
	public Return delete(Return request) throws Exception {
		return null;
	}

}
