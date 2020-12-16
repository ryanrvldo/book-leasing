package com.lawencon.bookleasing.repository;

import java.util.List;

import com.lawencon.bookleasing.entity.Inventory;

/**
 * @author Rian Rivaldo Rumapea
 */
public interface InventoryRepository extends BaseRepository<Inventory> {

  List<Inventory> getInventoryList() throws Exception;

}
