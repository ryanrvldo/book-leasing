package com.lawencon.bookleasing.dao.jdbc.template;

import com.lawencon.bookleasing.dao.RentalDetailDao;
import com.lawencon.bookleasing.entity.RentalDetail;

/**
 * @author Rian Rivaldo Rumapea
 */
public class RentalDetailDaoImpl extends BaseDaoImpl implements RentalDetailDao {

	@Override
	public RentalDetail insert(RentalDetail request) throws Exception {
		String sql = buildSQLQueryOf(
				"INSERT INTO tb_r_dtl_rental (rental_hdr_id, inventory_id, total_price, rental_date, return_date) ",
				"VALUES (?, ?, ?, ?, ?) ", "RETURNING id");
		return queryForObject(sql,
				(rs, rowNum) -> {
					RentalDetail rentalDetail = new RentalDetail();
					rentalDetail.setId(rs.getLong("id"));
					return rentalDetail;
				},
				request.getRentalHeader().getId(),
				request.getInventory().getId(),
				request.getTotalPrice(),
				request.getRentalDate(),
				request.getReturnDate()
		);
	}

	@Override
	public RentalDetail get(RentalDetail request) throws Exception {
		return null;
	}

	@Override
	public RentalDetail update(RentalDetail request) throws Exception {
		return null;
	}

	@Override
	public RentalDetail delete(RentalDetail request) throws Exception {
		return null;
	}

}
