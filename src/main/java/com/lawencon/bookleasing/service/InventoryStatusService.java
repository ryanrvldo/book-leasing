package com.lawencon.bookleasing.service;

import com.lawencon.bookleasing.entity.InventoryStatus;

/**
 * @author Rian Rivaldo
 */

public interface InventoryStatusService extends BaseService<InventoryStatus> {

  InventoryStatus getByStatus(String status) throws Exception;

}
