package com.lawencon.bookleasing.service.impl;

import com.lawencon.bookleasing.entity.RentalHeader;
import com.lawencon.bookleasing.entity.Return;
import com.lawencon.bookleasing.entity.User;
import com.lawencon.bookleasing.repository.RentalHeaderRepository;
import com.lawencon.bookleasing.repository.ReturnRepository;
import com.lawencon.bookleasing.service.ReturnBookService;
import com.lawencon.bookleasing.util.UserSessionCache;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * @author Rian Rivaldo Rumapea
 */
public class ReturnBookServiceImpl implements ReturnBookService {

	private final RentalHeaderRepository headerRepository;
	private final ReturnRepository returnRepository;
	private final UserSessionCache userSessionCache;
	private final TransactionTemplate transactionTemplate;

	public ReturnBookServiceImpl(RentalHeaderRepository headerRepository, ReturnRepository returnRepository,
	                             UserSessionCache userSessionCache, TransactionTemplate transactionTemplate) {
		this.headerRepository = headerRepository;
		this.returnRepository = returnRepository;
		this.userSessionCache = userSessionCache;
		this.transactionTemplate = transactionTemplate;
	}

	@Override
	public Return checkReceiptNumber(RentalHeader header) throws Exception {
		Optional.ofNullable(header.getReceipt())
				.orElseThrow(() -> new IllegalArgumentException("Receipt Number must be not null."));

		return transactionTemplate.execute(val -> {
			try {
				RentalHeader resultHeader = this.headerRepository.get(header);
				Return returnData = new Return();
				returnData.setRentalHeader(resultHeader);
				return this.returnRepository.get(returnData);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			}
		});
	}

	@Override
	public BigDecimal getTotalRentalCost(String receiptNumber) throws Exception {
		return transactionTemplate.execute(val -> {
			try {
				return this.returnRepository.getTotalRentalCost(receiptNumber);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			}
		});
	}

	@Override
	public void addReturnBook(RentalHeader header, Return returnData) throws Exception {
		User activeUser = Optional.ofNullable(this.userSessionCache.getActiveUser())
				.orElseThrow(() -> new NullPointerException("There is no active user."));
		returnData.setUser(activeUser);
		transactionTemplate.executeWithoutResult(val -> {
			try {
				this.returnRepository.add(returnData);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			}
		});
	}

}
