package com.lawencon.bookleasing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lawencon.bookleasing.entity.BookAuthor;

/**
 * @author Rian Rivaldo
 */
@Repository
public interface BookAuthorRepository extends JpaRepository<BookAuthor, Long> {

  @Query(value = "FROM BookAuthor WHERE book.id = ?1")
  BookAuthor findByBookId(Long bookId);

}
