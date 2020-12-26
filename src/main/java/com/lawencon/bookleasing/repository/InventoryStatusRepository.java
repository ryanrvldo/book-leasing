package com.lawencon.bookleasing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.bookleasing.entity.InventoryStatus;

/**
 * @author Rian Rivaldo
 */
@Repository
public interface InventoryStatusRepository extends JpaRepository<InventoryStatus, Long> {

  InventoryStatus findByStatus(String status) throws Exception;

}
