package com.lawencon.bookleasing.service.impl;

import java.util.Collections;
import java.util.List;

import com.lawencon.bookleasing.dao.InventoryDao;
import com.lawencon.bookleasing.dao.impl.InventoryDaoImpl;
import com.lawencon.bookleasing.model.Inventory;
import com.lawencon.bookleasing.service.InventoryService;

/**
 * @author Rian Rivaldo Rumapea
 */
public class InventoryServiceImpl implements InventoryService {

  private InventoryDao dao = new InventoryDaoImpl();

  @Override
  public List<Inventory> getInvetoryList() throws Exception {
    return Collections.unmodifiableList(dao.getAll());
  }

  @Override
  public Inventory add(Inventory item) throws Exception {
    return dao.insert(item);
  }

  @Override
  public Inventory get(Inventory item) throws Exception {
    return dao.get(item);
  }

}
