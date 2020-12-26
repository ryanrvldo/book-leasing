package com.lawencon.bookleasing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.bookleasing.entity.Publisher;

/**
 * @author Rian Rivaldo
 */
@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {

  Publisher findByCode(String code) throws Exception;

}
