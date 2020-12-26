package com.lawencon.bookleasing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lawencon.bookleasing.entity.Author;

/**
 * @author Rian Rivaldo
 */
@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

  Author findByFirstNameIgnoreCaseAndLastNameIgnoreCase(String firstName, String lastName) throws Exception;

  @Query(value = "SELECT a FROM Author a " +
      "INNER JOIN BookAuthor ba ON ba.author.id = a.id " +
      "WHERE ba.book.id = ?1 ")
  List<Author> findAllByBook(Long bookId) throws Exception;

}
