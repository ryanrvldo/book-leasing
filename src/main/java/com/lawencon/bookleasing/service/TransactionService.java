package com.lawencon.bookleasing.service;

import com.lawencon.bookleasing.model.TransactionResponse;

/**
 * @author Rian Rivaldo
 */

public interface TransactionService {

  String addRentalTransaction(String data) throws Exception;

  TransactionResponse getRentalTransaction(String reeciptNumber) throws Exception;

}
