package com.lawencon.bookleasing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lawencon.bookleasing.entity.Customer;

/**
 * @author Rian Rivaldo
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

  @Query(value = "FROM Customer c INNER JOIN c.profile AS p WHERE lower(p.email) = ?1")
  Customer findByEmail(String email) throws Exception;

}
