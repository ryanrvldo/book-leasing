package com.lawencon.bookleasing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.bookleasing.constants.Constants;
import com.lawencon.bookleasing.entity.Customer;
import com.lawencon.bookleasing.service.CustomerService;
import com.lawencon.bookleasing.util.WebResponseUtils;

/**
 * @author Rian Rivaldo
 */
@RestController
@RequestMapping(value = Constants.BASE_ENDPOINT)
public class CustomerController {

  private final CustomerService customerService;

  @Autowired
  public CustomerController(CustomerService customerService) {
	this.customerService = customerService;
  }

  @GetMapping(value = { "/customer" }, produces = { Constants.APPLICATION_JSON })
  public ResponseEntity<?> getCustomer(@RequestParam("email") String email) {
	try {
	  Customer customer = customerService.getCustomerByEmail(email);
	  return WebResponseUtils.createWebResponse(customer, HttpStatus.OK);
	} catch (Exception e) {
	  e.printStackTrace();
	  return WebResponseUtils.createWebResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
  }

  @GetMapping(value = { "/customers" }, produces = { Constants.APPLICATION_JSON })
  public ResponseEntity<?> getAllCustomer() {
	try {
	  return WebResponseUtils.createWebResponse(customerService.getAll(), HttpStatus.OK);
	} catch (Exception e) {
	  e.printStackTrace();
	  return WebResponseUtils.createWebResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
  }

}
