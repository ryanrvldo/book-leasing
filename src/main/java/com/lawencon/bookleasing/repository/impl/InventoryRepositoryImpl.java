package com.lawencon.bookleasing.repository.impl;

import com.lawencon.bookleasing.dao.InventoryDao;
import com.lawencon.bookleasing.entity.Inventory;
import com.lawencon.bookleasing.repository.InventoryRepository;

import java.util.Collections;
import java.util.List;

/**
 * @author Rian Rivaldo Rumapea
 */
public class InventoryRepositoryImpl implements InventoryRepository {

	private final InventoryDao dao;

	public InventoryRepositoryImpl(InventoryDao dao) {
		this.dao = dao;
	}

	@Override
	public List<Inventory> getInventoryList() throws Exception {
		return Collections.unmodifiableList(dao.getAll());
	}

	@Override
	public Inventory add(Inventory request) throws Exception {
		return dao.insert(request);
	}

	@Override
	public Inventory get(Inventory request) throws Exception {
		return dao.get(request);
	}

}
