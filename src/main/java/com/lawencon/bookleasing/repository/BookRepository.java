package com.lawencon.bookleasing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lawencon.bookleasing.entity.Book;

/**
 * @author Rian Rivaldo
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

  @Query("FROM Book b INNER JOIN b.language INNER JOIN b.publisher WHERE b.id = ?1")
  Book findByBookId(Long id) throws Exception;

  @Query("FROM Book b INNER JOIN b.language INNER JOIN b.publisher WHERE b.isbn = ?1")
  Book findByIsbn(String isbn) throws Exception;

  void deleteByIsbn(String isbn) throws Exception;

  @Override
  @Query("FROM Book b INNER JOIN b.language INNER JOIN b.publisher ")
  List<Book> findAll();

}
