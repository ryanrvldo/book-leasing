package com.lawencon.bookleasing.dao.hibernate.hql;

import com.lawencon.bookleasing.dao.InventoryDao;
import com.lawencon.bookleasing.entity.Book;
import com.lawencon.bookleasing.entity.Inventory;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rian Rivaldo Rumapea
 */
public class InventoryDaoImpl extends BaseDaoImpl<Inventory> implements InventoryDao {

	@Override
	public void insert(Inventory data) throws Exception {
		insertData(data);
	}

	@Override
	public Inventory get(Inventory data) throws Exception {
		String hql = buildSQLQueryOf(
				"SELECT DISTINCT i.id, b.isbn, b.rentalCost ",
				"FROM Inventory AS i ",
				"INNER JOIN i.book AS b ",
				"LEFT JOIN RentalDetail AS dtl ON dtl.inventory.id = i.id ",
				"LEFT JOIN Return AS r ON r.rentalHeader.id = dtl.rentalHeader.id ",
				"WHERE (r.returnedAt <= ?1 OR dtl.id IS NULL) AND b.isbn = ?2 ");
		List<Inventory> inventoryList = new ArrayList<>();
		List<Object[]> objList = getSession()
				.createQuery(hql, Object[].class)
				.setParameter(1, LocalDateTime.now())
				.setParameter(2, data.getBook().getIsbn())
				.list();
		objList.forEach(objArr -> {
			Inventory inventory = new Inventory();
			inventory.setId(((Long) objArr[0]));

			Book book = new Book();
			book.setIsbn(((String) objArr[1]));
			book.setRentalCost((BigDecimal) objArr[2]);
			inventory.setBook(book);
			inventoryList.add(inventory);
		});
		return singleResultFromList(inventoryList);
	}

	@Override
	public void update(Inventory data) throws Exception {
		mergeUpdateData(data);
	}

	@Override
	public void delete(Inventory data) throws Exception {
		deleteData(data);
	}

	@Override
	public List<Inventory> getAll() throws Exception {
		String hql = buildSQLQueryOf(
				"SELECT DISTINCT i.id, b.id, b.title, b.isbn, b.rentalCost, b.publicationYear ",
				"FROM Inventory AS i ",
				"INNER JOIN i.book AS b ",
				"LEFT JOIN RentalDetail AS dtl ON dtl.inventory.id = i.id ",
				"LEFT JOIN Return AS r ON r.rentalHeader.id = dtl.rentalHeader.id ",
				"WHERE r.returnedAt <= ?1 OR dtl.id IS null ");
		List<Inventory> inventoryList = new ArrayList<>();
		List<Object[]> objList = getSession()
				.createQuery(hql, Object[].class)
				.setParameter(1, LocalDateTime.now())
				.list();
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
			inventoryList.add(inventory);
		});
		return inventoryList;
	}

}
