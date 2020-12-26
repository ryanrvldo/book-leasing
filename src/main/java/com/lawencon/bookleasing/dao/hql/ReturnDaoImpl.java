package com.lawencon.bookleasing.dao.hql;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.bookleasing.dao.ReturnDao;
import com.lawencon.bookleasing.entity.RentalHeader;
import com.lawencon.bookleasing.entity.Return;

/**
 * @author Rian Rivaldo
 */
@Repository("return-hql")
public class ReturnDaoImpl extends BaseDaoImpl<Return> implements ReturnDao {

  public ReturnDaoImpl() {
	super(Return.class);
  }

  @Override
  public Double getRentalCost(String receiptNumber) throws Exception {
	String hql = buildQueryOf(
	    "SELECT sum(detail.totalPrice) ",
	    "FROM Return AS return ",
	    "RIGHT JOIN return.rentalHeader AS header ",
	    "RIGHT JOIN RentalDetail AS detail ON detail.rentalHeader.id = header.id ",
	    "GROUP BY header.id, header.receipt ",
	    "HAVING header.receipt = ?1 ");
	Object obj = getEntityManager()
	    .createQuery(hql, Object.class)
	    .setParameter(1, receiptNumber)
	    .getSingleResult();
	BigDecimal rentalCost = (BigDecimal) obj;
	return rentalCost.doubleValue();
  }

  @Override
  public Return findById(Long id) throws Exception {
	String hql = buildQueryOf(
	    "FROM Return ",
	    "WHERE id = ?1");
	return getSingleResultById(hql, id);
  }

  @Override
  public Return findByHeaderId(Long headerId) throws Exception {
	String hql = buildQueryOf(
	    "SELECT hdr.id, hdr.receipt ",
	    "FROM Return r ",
	    "RIGHT JOIN r.rentalHeader hdr ",
	    "WHERE r.id is null AND hdr.id = ?1 ");
	Object[] objArr = getEntityManager()
	    .createQuery(hql, Object[].class)
	    .setParameter(1, headerId)
	    .getSingleResult();
	RentalHeader header = new RentalHeader();
	header.setId((Long) objArr[0]);
	header.setReceipt((String) objArr[1]);

	Return returnData = new Return();
	returnData.setRentalHeader(header);
	return returnData;
  }

  @Override
  public void insert(Return data) throws Exception {
	insertData(data);
  }

  @Override
  public void update(Return data) throws Exception {
	mergeUpdateData(data);
  }

  @Override
  public void deleteById(Long id) throws Exception {
	String hql = buildQueryOf(
	    "FROM Return ",
	    "WHERE id = ?1");
	deleteEntityById(hql, id);
  }

  @Override
  public void delete(Return data) throws Exception {
	deleteData(data);
  }

  @Override
  public List<Return> findAll() throws Exception {
	return getAllData();
  }

}
