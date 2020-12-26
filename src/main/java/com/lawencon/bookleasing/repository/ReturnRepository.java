package com.lawencon.bookleasing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lawencon.bookleasing.entity.Return;

/**
 * @author Rian Rivaldo
 */
@Repository
public interface ReturnRepository extends JpaRepository<Return, Long> {

//  @Query(value = "SELECT sum(detail.totalPrice) " +
//      "FROM Return AS return " +
//      "RIGHT JOIN return.rentalHeader AS header " +
//      "RIGHT JOIN RentalDetail AS detail ON detail.rentalHeader.id = header.id " +
//      "GROUP BY header.id, header.receipt " +
//      "HAVING header.receipt = ?1 ")
//  BigDecimal sumRentalCost(String receiptNumber) throws Exception;

  @Query(value = "SELECT hdr.id, hdr.receipt " +
      "FROM Return r " +
      "RIGHT JOIN r.rentalHeader hdr " +
      "WHERE r.id is null AND hdr.id = ?1 ")
  Return findByHeaderId(Long headerId) throws Exception;
}
