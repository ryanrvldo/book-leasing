package com.lawencon.bookleasing.dao.hibernate.hql;

import com.lawencon.bookleasing.dao.ReturnDao;
import com.lawencon.bookleasing.entity.RentalHeader;
import com.lawencon.bookleasing.entity.Return;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rian Rivaldo Rumapea
 */
public class ReturnDaoImpl extends BaseDaoImpl<Return> implements ReturnDao {

	@Override
	public Double getRentalCost(String receiptNumber) throws Exception {
		String hql = buildSQLQueryOf(
				"SELECT sum(detail.totalPrice) ",
				"FROM Return AS return ",
				"RIGHT JOIN return.rentalHeader AS header ",
				"RIGHT JOIN RentalDetail AS detail ON detail.rentalHeader.id = header.id ",
				"GROUP BY header.id, header.receipt ",
				"HAVING header.receipt = ?1 ");
		List<Double> doubleList = new ArrayList<>();
		List<Object> objList = getSession()
				.createQuery(hql, Object.class)
				.setParameter(1, receiptNumber)
				.list();
		objList.forEach(obj -> {
			BigDecimal rentalCost = (BigDecimal) obj;
			doubleList.add(rentalCost.doubleValue());
		});
		return doubleList.size() > 0 ? doubleList.get(0) : null;
	}

	@Override
	public void insert(Return request) throws Exception {
		insertData(request);
	}

	@Override
	public Return get(Return request) throws Exception {
		String hql = buildSQLQueryOf(
				"SELECT hdr.id, hdr.receipt ",
				"FROM Return r ",
				"RIGHT JOIN r.rentalHeader hdr ",
				"WHERE r.id is null AND hdr.id = ?1 ");
		List<Return> returnList = new ArrayList<>();
		List<Object[]> objList = getSession()
				.createQuery(hql, Object[].class)
				.setParameter(1, request.getRentalHeader().getId())
				.list();
		objList.forEach(objArr -> {
			RentalHeader header = new RentalHeader();
			header.setId((Long) objArr[0]);
			header.setReceipt((String) objArr[1]);

			Return returnData = new Return();
			returnData.setRentalHeader(header);
			returnList.add(returnData);
		});
		return singleResultFromList(returnList);
	}

	@Override
	public void update(Return request) throws Exception {
		mergeUpdateData(request);
	}

	@Override
	public void delete(Return request) throws Exception {
		deleteData(request);
	}

	@Override
	public List<Return> getAll() throws Exception {
		return getAllData(Return.class);
	}

}
