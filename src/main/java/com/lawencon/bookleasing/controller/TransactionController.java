package com.lawencon.bookleasing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.bookleasing.constants.Constants;
import com.lawencon.bookleasing.service.TransactionService;
import com.lawencon.bookleasing.util.WebResponseUtils;

/**
 * @author Rian Rivaldo
 */
@RestController
@RequestMapping(value = Constants.BASE_ENDPOINT + "/transaction")
public class TransactionController {

  private final TransactionService transactionService;

  @Autowired
  public TransactionController(TransactionService transactionService) {
	this.transactionService = transactionService;
  }

  @PostMapping(value = { "/rental" }, consumes = { Constants.APPLICATION_JSON }, produces = {
      Constants.APPLICATION_JSON })
  private ResponseEntity<?> createRentalTransaction(@RequestBody String body) {
	try {
	  String receiptNumber = this.transactionService.addRentalTransaction(body);
	  return WebResponseUtils.createWebResponse("Success added transaction with receipt number: " + receiptNumber,
	      HttpStatus.CREATED);
	} catch (Exception e) {
	  e.printStackTrace();
	  return WebResponseUtils.createWebResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
  }

  @GetMapping(value = { "/rental/{receipt}" }, produces = { Constants.APPLICATION_JSON })
  public ResponseEntity<?> getTransaction(@PathVariable("receipt") String receipt) {
	try {
	  return WebResponseUtils.createWebResponse(transactionService.getRentalTransaction(receipt), HttpStatus.OK);
	} catch (Exception e) {
	  e.printStackTrace();
	  return WebResponseUtils.createWebResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
  }

}
