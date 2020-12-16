package com.lawencon.bookleasing.dao.jdbcImpl;

import com.lawencon.bookleasing.dao.InventoryDao;
import com.lawencon.bookleasing.entity.Book;
import com.lawencon.bookleasing.entity.Inventory;

import java.util.List;

/**
 * @author Rian Rivaldo Rumapea
 */
public class InventoryDaoJdbcImpl extends BaseDaoJdbcImpl implements InventoryDao {

	@Override
	public Inventory insert(Inventory request) throws Exception {
		String sql = buildSQLQueryOf(
				"INSERT INTO tb_m_inventory (book_id) ",
				"VALUES (?) ",
				"RETURNING id ; ");
		return queryForObject(sql,
				(rs, rowNum) -> {
					Inventory inventory = new Inventory();
					inventory.setId(rs.getLong("id"));
					return inventory;
				},
				request.getBook().getId()
		);
	}

	@Override
	public Inventory get(Inventory request) throws Exception {
		String sql = buildSQLQueryOf(
				"SELECT i.id, b.isbn, b.rental_cost ",
				"FROM tb_m_inventory AS i ",
				"LEFT JOIN tb_m_book AS b ON b.id = i.book_id ",
				"LEFT JOIN tb_r_dtl_rental dtl ON dtl.inventory_id = i.id ",
				"LEFT JOIN tb_r_return rtr ON rtr.rental_hdr_id = dtl .rental_hdr_id ",
				"WHERE (rtr.returned_at <= now() OR dtl.id IS NULL) AND b.isbn = ? ;");
		return queryForObject(sql,
				(rs, rowNum) -> {
					Book book = new Book();
					book.setIsbn(rs.getString("isbn"));
					book.setRentalCost(rs.getBigDecimal("rental_cost"));
					Inventory inventory = new Inventory();
					inventory.setId(rs.getLong("id"));
					inventory.setBook(book);
					return inventory;
				},
				request.getBook().getIsbn()
		);
	}

	@Override
	public Inventory update(Inventory newData) throws Exception {
		return null;
	}

	@Override
	public Inventory delete(Inventory data) throws Exception {
		return null;
	}

	@Override
	public List<Inventory> getAll() throws Exception {
		String sql = buildSQLQueryOf(
				"SELECT i.id AS inventory_id, b.id AS book_id, b.title, b.rental_cost, b.isbn, b.publication_year ",
				"FROM tb_m_inventory AS i ",
				"LEFT JOIN tb_m_book AS b ON b.id = i.book_id ",
				"LEFT JOIN tb_r_dtl_rental dtl ON dtl.inventory_id = i.id ",
				"LEFT JOIN tb_r_return rtr ON rtr.rental_hdr_id = dtl.rental_hdr_id ",
				"WHERE rtr.returned_at <= now() OR dtl.id IS null;");

		return query(sql, (rs, rowNum) -> {
			Book book = new Book();
			book.setId(rs.getLong("book_id"));
			book.setTitle(rs.getString("title"));
			book.setRentalCost(rs.getBigDecimal("rental_cost"));
			book.setIsbn(rs.getString("isbn"));
			book.setPublicationYear(rs.getShort("publication_year"));

			Inventory inventory = new Inventory();
			inventory.setId(rs.getLong("inventory_id"));
			inventory.setBook(book);
			return inventory;
		});
	}

}
