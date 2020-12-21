package com.lawencon.bookleasing.repository.impl;

import com.lawencon.bookleasing.dao.ReturnDao;
import com.lawencon.bookleasing.entity.Return;
import com.lawencon.bookleasing.repository.ReturnRepository;

import java.math.BigDecimal;

/**
 * @author Rian Rivaldo Rumapea
 */
public class ReturnRepositoryImpl implements ReturnRepository {

	private final ReturnDao dao;

	public ReturnRepositoryImpl(ReturnDao dao) {
		this.dao = dao;
	}

	@Override
	public BigDecimal getTotalRentalCost(String receiptNumber) throws Exception {
		return BigDecimal.valueOf(this.dao.getRentalCost(receiptNumber));
	}

	@Override
	public void add(Return request) throws Exception {
		this.dao.insert(request);
	}

	@Override
	public Return get(Return request) throws Exception {
		return this.dao.get(request);
	}

}
