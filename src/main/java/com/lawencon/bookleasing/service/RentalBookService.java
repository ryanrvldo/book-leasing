package com.lawencon.bookleasing.service;

import com.lawencon.bookleasing.entity.Inventory;
import com.lawencon.bookleasing.entity.RentalDetail;
import com.lawencon.bookleasing.entity.RentalHeader;

import java.util.List;

/**
 * @author Rian Rivaldo Rumapea
 */
public interface RentalBookService {

	List<Inventory> getInventoryList() throws Exception;

	Inventory getInventoryByIsbn(String isbn) throws Exception;

	void addRentalTransaction(RentalHeader header, List<RentalDetail> details) throws Exception;

}
