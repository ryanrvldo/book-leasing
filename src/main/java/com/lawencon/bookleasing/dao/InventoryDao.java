package com.lawencon.bookleasing.dao;

import com.lawencon.bookleasing.entity.Inventory;

import java.util.List;

/**
 * @author Rian Rivaldo Rumapea
 */
public interface InventoryDao extends BaseDao<Inventory> {

	List<Inventory> getAll() throws Exception;

}
