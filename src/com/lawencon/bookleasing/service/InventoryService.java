package com.lawencon.bookleasing.service;

import java.util.List;

import com.lawencon.bookleasing.model.Inventory;

/**
 * @author Rian Rivaldo Rumapea
 */
public interface InventoryService extends BaseDaoService<Inventory> {

  List<Inventory> getInvetoryList() throws Exception;

}
