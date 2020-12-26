package com.lawencon.bookleasing.dao.jpa;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.bookleasing.dao.RentalDetailDao;
import com.lawencon.bookleasing.entity.Book;
import com.lawencon.bookleasing.entity.Customer;
import com.lawencon.bookleasing.entity.Inventory;
import com.lawencon.bookleasing.entity.Profile;
import com.lawencon.bookleasing.entity.RentalDetail;
import com.lawencon.bookleasing.entity.RentalHeader;
import com.lawencon.bookleasing.repository.RentalDetailRepository;

/**
 * @author Rian Rivaldo
 */
@Repository("rental-dtl-jpa")
public class RentalDetailDaoJpaImpl implements RentalDetailDao {

  private final RentalDetailRepository repository;

  @Autowired
  public RentalDetailDaoJpaImpl(RentalDetailRepository repository) {
	this.repository = repository;
  }

  @Override
  public RentalDetail findById(Long id) throws Exception {
	return repository.findById(id).get();
  }

  @Override
  public void insert(RentalDetail data) throws Exception {
	repository.save(data);
  }

  @Override
  public void update(RentalDetail data) throws Exception {
	repository.save(data);
  }

  @Override
  public void delete(RentalDetail data) throws Exception {
	repository.delete(data);
  }

  @Override
  public void deleteById(Long id) throws Exception {
	repository.deleteById(id);
  }

  @Override
  public List<RentalDetail> findAll() throws Exception {
	return repository.findAll();
  }

  @Override
  public List<RentalDetail> findAllByReceiptNumber(String receiptNumber) throws Exception {
	List<RentalDetail> details = new ArrayList<>();
	List<Object[]> objList = repository.findAllByReceiptNumber(receiptNumber);
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
