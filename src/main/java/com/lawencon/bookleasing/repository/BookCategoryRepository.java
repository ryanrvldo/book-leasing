package com.lawencon.bookleasing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lawencon.bookleasing.entity.BookCategory;

/**
 * @author Rian Rivaldo
 */

public interface BookCategoryRepository extends JpaRepository<BookCategory, Long> {

}
