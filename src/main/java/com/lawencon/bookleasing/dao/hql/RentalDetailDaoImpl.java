package com.lawencon.bookleasing.dao.hql;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.bookleasing.dao.RentalDetailDao;
import com.lawencon.bookleasing.entity.Book;
import com.lawencon.bookleasing.entity.Customer;
import com.lawencon.bookleasing.entity.Inventory;
import com.lawencon.bookleasing.entity.Profile;
import com.lawencon.bookleasing.entity.RentalDetail;
import com.lawencon.bookleasing.entity.RentalHeader;

/**
 * @author Rian Rivaldo
 */
@Repository("rental-dtl-hql")
public class RentalDetailDaoImpl extends BaseDaoImpl<RentalDetail> implements RentalDetailDao {

  public RentalDetailDaoImpl() {
	super(RentalDetail.class);
  }

  @Override
  public RentalDetail findById(Long id) throws Exception {
	String hql = buildQueryOf(
	    "FROM RentalDetail ",
	    "WHERE code = ?1 ");
	return getSingleResultById(hql, id);
  }

  @Override
  public void insert(RentalDetail data) throws Exception {
	insertData(data);
  }

  @Override
  public void update(RentalDetail data) throws Exception {
	mergeUpdateData(data);
  }

  @Override
  public void deleteById(Long id) throws Exception {
	String hql = buildQueryOf(
	    "DELETE FROM RentalDetail ",
	    "WHERE id = ?1 ");
	deleteEntityById(hql, id);
  }

  @Override
  public void delete(RentalDetail data) throws Exception {
	deleteData(data);
  }

  @Override
  public List<RentalDetail> findAll() throws Exception {
	return getAllData();
  }

  @Override
  public List<RentalDetail> findAllByReceiptNumber(String receiptNumber) throws Exception {
	String hql = buildQueryOf(
	    "SELECT p, b.title, b.isbn, dtl.rentalDate, dtl.returnDate ",
	    "FROM RentalDetail dtl ",
	    "INNER JOIN RentalHeader hdr ON hdr.id = dtl.rentalHeader.id ",
	    "INNER JOIN Customer c ON c.id = hdr.customer.id ",
	    "INNER JOIN Profile p ON p.id = c.profile.id ",
	    "INNER JOIN Inventory i ON i.id = dtl.inventory.id ",
	    "INNER JOIN Book b ON b.id = i.book.id ",
	    "WHERE lower(hdr.receipt) = lower(?1) ");
	List<RentalDetail> details = new ArrayList<>();
	List<Object[]> objList = getEntityManager()
	    .createQuery(hql, Object[].class)
	    .setParameter(1, receiptNumber)
	    .getResultList();
	objList.forEach(objArr -> {
	  Profile profile = (Profile) objArr[0];
	  Customer customer = new Customer();
	  customer.setProfile(profile);

	  Book book = new Book();
	  book.setTitle((String) objArr[1]);
	  book.setIsbn((String) objArr[2]);
	  Inventory inventory = new Inventory();
	  inventory.setBook(book);

	  RentalHeader header = new RentalHeader();
	  header.setCustomer(customer);
	  header.setReceipt(receiptNumber);

	  LocalDateTime rentalDate = (LocalDateTime) objArr[3];
	  LocalDateTime returnDate = (LocalDateTime) objArr[4];
	  RentalDetail detail = new RentalDetail();
	  detail.setRentalHeader(header);
	  detail.setInventory(inventory);
	  detail.setRentalDate(rentalDate);
	  detail.setReturnDate(returnDate);

	  details.add(detail);
	});
	return details;
  }

}
