package com.lawencon.bookleasing.dao;

import java.util.List;

import com.lawencon.bookleasing.entity.Inventory;

/**
 * @author Rian Rivaldo
 */
public interface InventoryDao extends BaseDao<Inventory> {

  Inventory findByBookIsbn(String isbn) throws Exception;

  List<Inventory> findAllAvailableInventory() throws Exception;

}
