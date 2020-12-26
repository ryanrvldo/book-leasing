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
import com.lawencon.bookleasing.entity.InventoryStatus;
import com.lawencon.bookleasing.service.InventoryService;
import com.lawencon.bookleasing.util.WebResponseUtils;

/**
 * @author Rian Rivaldo
 */
@RestController
@RequestMapping(value = { Constants.BASE_ENDPOINT })
public class InventoryController {

  private final InventoryService inventoryService;

  @Autowired
  public InventoryController(InventoryService inventoryService) {
	this.inventoryService = inventoryService;
  }

  @GetMapping(value = { "/inventories" }, produces = { Constants.APPLICATION_JSON })
  public ResponseEntity<?> getAllInventory() {
	try {
	  return WebResponseUtils.createWebResponse(inventoryService.getAll(), HttpStatus.OK);
	} catch (Exception e) {
	  e.printStackTrace();
	  return WebResponseUtils.createWebResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
  }

  @GetMapping(value = { "/inventories/status/{id}" })
  public ResponseEntity<?> getAllInventoryByStatusId(@PathVariable("id") Long id) {
	try {
	  return WebResponseUtils.createWebResponse(inventoryService.getAllInventoriesByStatusId(id), HttpStatus.OK);
	} catch (Exception e) {
	  e.printStackTrace();
	  return WebResponseUtils.createWebResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
  }

  @GetMapping(value = { "/inventories/status" })
  public ResponseEntity<?> getAllInventoryStatus() {
	try {
	  return WebResponseUtils.createWebResponse(inventoryService.getAllInventoryStatus(), HttpStatus.OK);
	} catch (Exception e) {
	  e.printStackTrace();
	  return WebResponseUtils.createWebResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
  }

  @PostMapping(value = { "/inventory/status" }, consumes = { Constants.APPLICATION_JSON }, produces = {
      Constants.APPLICATION_JSON })
  public ResponseEntity<?> createInventoryStatus(@RequestBody InventoryStatus requestBody) {
	try {
	  inventoryService.createInventoryStatus(requestBody);
	  return WebResponseUtils.createWebResponse(requestBody, HttpStatus.CREATED);
	} catch (Exception e) {
	  e.printStackTrace();
	  return WebResponseUtils.createWebResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
  }
}
