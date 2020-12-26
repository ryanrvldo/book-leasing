package com.lawencon.bookleasing.service;

import java.util.List;

import com.lawencon.bookleasing.entity.Inventory;
import com.lawencon.bookleasing.entity.InventoryStatus;

/**
 * @author Rian Rivaldo Rumapea
 */
public interface InventoryService extends BaseService<Inventory> {

  Inventory getInventoryById(Long id) throws Exception;

  Inventory getInventoryByBookIsbn(String isbn) throws Exception;

  List<Inventory> getInventoryList() throws Exception;

  List<Inventory> getAllInventoriesByStatusId(Long statusId) throws Exception;

  List<InventoryStatus> getAllInventoryStatus() throws Exception;

  void createInventoryStatus(InventoryStatus inventoryStatus) throws Exception;

}
