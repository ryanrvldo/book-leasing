package com.lawencon.bookleasing.repository;

import com.lawencon.bookleasing.entity.Inventory;

import java.util.List;

/**
 * @author Rian Rivaldo Rumapea
 */
public interface InventoryRepository extends BaseRepository<Inventory> {

	List<Inventory> getInventoryList() throws Exception;

}
