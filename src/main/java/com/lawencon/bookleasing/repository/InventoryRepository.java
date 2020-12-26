package com.lawencon.bookleasing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lawencon.bookleasing.entity.Inventory;

/**
 * @author Rian Rivaldo
 */
@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

  @Query(value = "FROM Inventory i INNER JOIN i.book b WHERE b.isbn = ?1")
  Inventory findByBookIsbn(String isbn) throws Exception;

  @Query(value = "SELECT i.id, b.id, b.title, b.isbn, b.rentalCost, b.publicationYear, s "
      + "FROM Inventory i "
      + "INNER JOIN i.status s "
      + "INNER JOIN i.book b ")
  List<Object[]> findAllBook() throws Exception;

}
