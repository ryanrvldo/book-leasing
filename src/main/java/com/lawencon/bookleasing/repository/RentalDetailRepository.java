package com.lawencon.bookleasing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lawencon.bookleasing.entity.RentalDetail;

/**
 * @author Rian Rivaldo
 */
@Repository
public interface RentalDetailRepository extends JpaRepository<RentalDetail, Long> {

  @Query(value = "SELECT p, b.title, b.isbn, dtl.rentalDate, dtl.returnDate " +
      "FROM RentalDetail dtl " +
      "INNER JOIN dtl.inventory i " +
      "INNER JOIN i.book b " +
      "INNER JOIN dtl.rentalHeader hdr " +
      "INNER JOIN hdr.customer c " +
      "INNER JOIN c.profile p " +
      "WHERE lower(hdr.receipt) = lower(?1) ")
  List<Object[]> findAllByReceiptNumber(String receipt) throws Exception;

}
