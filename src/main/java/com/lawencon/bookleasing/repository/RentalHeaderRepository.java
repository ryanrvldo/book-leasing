package com.lawencon.bookleasing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.bookleasing.entity.RentalHeader;

/**
 * @author Rian Rivaldo
 */
@Repository
public interface RentalHeaderRepository extends JpaRepository<RentalHeader, Long> {

  RentalHeader findByReceipt(String receipt);

}
