package com.lawencon.bookleasing.dao.jpa;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.bookleasing.dao.InventoryDao;
import com.lawencon.bookleasing.entity.Book;
import com.lawencon.bookleasing.entity.Inventory;
import com.lawencon.bookleasing.entity.InventoryStatus;
import com.lawencon.bookleasing.repository.InventoryRepository;

/**
 * @author Rian Rivaldo
 */
@Repository("inventory-jpa")
public class InventoryDaoJpaImpl implements InventoryDao {

  private final InventoryRepository repository;

  @Autowired
  public InventoryDaoJpaImpl(InventoryRepository repository) {
	this.repository = repository;
  }

  @Override
  public Inventory findById(Long id) throws Exception {
	return repository.findById(id).get();
  }

  @Override
  public Inventory findByBookIsbn(String isbn) throws Exception {
	return repository.findByBookIsbn(isbn);
  }

  @Override
  public void insert(Inventory data) throws Exception {
	repository.save(data);
  }

  @Override
  public void update(Inventory data) throws Exception {
	repository.save(data);
  }

  @Override
  public void delete(Inventory data) throws Exception {
	repository.delete(data);
  }

  @Override
  public void deleteById(Long id) throws Exception {
	repository.deleteById(id);
  }

  @Override
  public List<Inventory> findAll() throws Exception {
	List<Object[]> objList = repository.findAllBook();

	List<Inventory> inventoryList = new ArrayList<>();
	objList.forEach(objArr -> {
	  Inventory inventory = new Inventory();
	  inventory.setId((Long) objArr[0]);

	  Book book = new Book();
	  book.setId((Long) objArr[1]);
	  book.setTitle((String) objArr[2]);
	  book.setIsbn(((String) objArr[3]));
	  book.setRentalCost((BigDecimal) objArr[4]);
	  book.setPublicationYear((Short) objArr[5]);
	  inventory.setBook(book);

	  InventoryStatus status = (InventoryStatus) objArr[6];
	  inventory.setStatus(status);
	  inventoryList.add(inventory);
	});
	return inventoryList;
  }

  @Override
  public List<Inventory> findAllAvailableInventory() throws Exception {
	// TODO Auto-generated method stub
	return null;
  }

}
