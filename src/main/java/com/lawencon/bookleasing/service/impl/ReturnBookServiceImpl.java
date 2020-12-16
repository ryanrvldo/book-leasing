package com.lawencon.bookleasing.service.impl;

import com.lawencon.bookleasing.entity.RentalHeader;
import com.lawencon.bookleasing.entity.Return;
import com.lawencon.bookleasing.entity.User;
import com.lawencon.bookleasing.model.UserSessionCache;
import com.lawencon.bookleasing.repository.RentalHeaderRepository;
import com.lawencon.bookleasing.repository.ReturnRepository;
import com.lawencon.bookleasing.service.ReturnBookService;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * @author Rian Rivaldo Rumapea
 */
public class ReturnBookServiceImpl implements ReturnBookService {

	private final RentalHeaderRepository headerRepository;
	private final ReturnRepository returnRepository;
	private final UserSessionCache userSessionCache;

	public ReturnBookServiceImpl(RentalHeaderRepository headerRepository, ReturnRepository returnRepository,
	                             UserSessionCache userSessionCache) {
		this.headerRepository = headerRepository;
		this.returnRepository = returnRepository;
		this.userSessionCache = userSessionCache;
	}

	@Override
	public Return checkReceiptNumber(RentalHeader header) throws Exception {
		Optional.ofNullable(header.getReceipt())
				.orElseThrow(() -> new IllegalArgumentException("Receipt Number must be not null."));

		RentalHeader resultHeader = Optional.ofNullable(this.headerRepository.get(header))
				.orElseThrow(() -> new NullPointerException(
						"Receipt Number is invalid. Make sure you have inputted valid receipt number."));
		Return returnData = new Return();
		returnData.setRentalHeader(resultHeader);
		return Optional.ofNullable(this.returnRepository.get(returnData))
				.orElseThrow(() -> new IllegalArgumentException("Books with that receipt number have been returned!"));
	}

	@Override
	public BigDecimal getTotalRentalCost(String receiptNumber) throws Exception {
		return Optional.ofNullable(this.returnRepository.getTotalRentalCost(receiptNumber))
				.orElseThrow();
	}

	@Override
	public void addReturnBook(RentalHeader header, Return returnData) throws Exception {
		User activeUser = Optional.ofNullable(this.userSessionCache.getActiveUser())
				.orElseThrow(() -> new NullPointerException("There is no active user."));
		returnData.setUser(activeUser);
		Optional.ofNullable(this.returnRepository.add(returnData))
				.orElseThrow(() -> new Exception("Failed to add data to database."));
	}

}
