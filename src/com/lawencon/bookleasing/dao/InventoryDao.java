package com.lawencon.bookleasing.dao;

import java.util.List;

import com.lawencon.bookleasing.model.Inventory;

/**
 * @author Rian Rivaldo Rumapea
 */
public interface InventoryDao extends BaseDao<Inventory> {

  List<Inventory> getAll() throws Exception;

}
