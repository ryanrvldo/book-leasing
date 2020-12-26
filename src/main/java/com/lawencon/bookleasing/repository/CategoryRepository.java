package com.lawencon.bookleasing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lawencon.bookleasing.entity.Category;

/**
 * @author Rian Rivaldo
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

  Category findByName(String name);

  @Query(value = "SELECT c FROM Category c " +
      "INNER JOIN BookCategory bc ON bc.category.id = c.id " +
      "WHERE bc.book.id = ?1 ")
  List<Category> findAllByBook(Long bookId) throws Exception;

}
