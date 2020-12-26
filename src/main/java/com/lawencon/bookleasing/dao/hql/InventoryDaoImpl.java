package com.lawencon.bookleasing.dao.hql;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.bookleasing.dao.InventoryDao;
import com.lawencon.bookleasing.entity.Book;
import com.lawencon.bookleasing.entity.Inventory;
import com.lawencon.bookleasing.entity.InventoryStatus;

/**
 * @author Rian Rivaldo
 */
@Repository("inventory-hql")
public class InventoryDaoImpl extends BaseDaoImpl<Inventory> implements InventoryDao {

  public InventoryDaoImpl() {
	super(Inventory.class);
  }

  @Override
  public Inventory findById(Long id) throws Exception {
	String hql = buildQueryOf(
	    "FROM Inventory ",
	    "WHERE id = ?1 ");
	return getSingleResultFromEntity(hql, id);
  }

  @Override
  public Inventory findByBookIsbn(String isbn) throws Exception {
	String hql = buildQueryOf(
	    "FROM Inventory AS i ",
	    "WHERE i.book.isbn = ?1 ");
	return getSingleResultFromEntity(hql, isbn);
  }

  @Override
  public void insert(Inventory data) throws Exception {
	insertData(data);
  }

  @Override
  public void update(Inventory data) throws Exception {
	mergeUpdateData(data);
  }

  @Override
  public void deleteById(Long id) throws Exception {
	String hql = buildQueryOf(
	    "DELETE FROM Inventory ",
	    "WHERE id = ?1 ");
	deleteEntityById(hql, id);
  }

  @Override
  public void delete(Inventory data) throws Exception {
	deleteData(data);
  }

  @Override
  public List<Inventory> findAll() throws Exception {
	String hql = buildQueryOf(
	    "SELECT i.id, b.id, b.title, b.isbn, b.rentalCost, b.publicationYear, s ",
	    "FROM Inventory AS i ",
	    "INNER JOIN i.book AS b ",
	    "INNER JOIN i.status AS s ");
	List<Inventory> inventoryList = new ArrayList<>();
	List<Object[]> objList = getEntityManager()
	    .createQuery(hql, Object[].class)
	    .getResultList();
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

	return null;
  }

}
