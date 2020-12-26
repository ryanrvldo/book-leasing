package com.lawencon.bookleasing.dao;

import com.lawencon.bookleasing.entity.InventoryStatus;

/**
 * @author Rian Rivaldo
 */

public interface InventoryStatusDao extends BaseDao<InventoryStatus> {

  InventoryStatus findByStatus(String status) throws Exception;

}
