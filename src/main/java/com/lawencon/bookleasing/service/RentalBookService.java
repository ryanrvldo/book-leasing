package com.lawencon.bookleasing.service;

import java.util.List;

import com.lawencon.bookleasing.entity.Inventory;
import com.lawencon.bookleasing.entity.RentalDetail;
import com.lawencon.bookleasing.entity.RentalHeader;

/**
 * @author Rian Rivaldo Rumapea
 */
public interface RentalBookService {

  List<Inventory> getInventoryList() throws Exception;

  Inventory getInventoryByIsbn(String isbn) throws Exception;

  void addRentalTransaction(RentalHeader header, List<RentalDetail> details) throws Exception;

}
