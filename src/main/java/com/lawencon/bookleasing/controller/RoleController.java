package com.lawencon.bookleasing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.bookleasing.constants.Constants;
import com.lawencon.bookleasing.entity.Role;
import com.lawencon.bookleasing.service.RoleService;
import com.lawencon.bookleasing.util.WebResponseUtils;

/**
 * @author Rian Rivaldo
 */
@RestController
@RequestMapping(value = { Constants.BASE_ENDPOINT })
public class RoleController {

  private final RoleService roleService;

  @Autowired
  public RoleController(RoleService roleService) {
	this.roleService = roleService;
  }

  @GetMapping(value = "/role", produces = { Constants.APPLICATION_JSON })
  public ResponseEntity<?> getRoleByCode(@RequestParam("code") String code) {
	try {
	  return WebResponseUtils.createWebResponse(roleService.getRoleByCode(code), HttpStatus.OK);
	} catch (Exception e) {
	  e.printStackTrace();
	  return WebResponseUtils.createWebResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
  }

  @GetMapping(value = "/roles", produces = { Constants.APPLICATION_JSON })
  public ResponseEntity<?> getAllRole() {
	try {
	  return WebResponseUtils.createWebResponse(roleService.getAll(), HttpStatus.OK);
	} catch (Exception e) {
	  e.printStackTrace();
	  return WebResponseUtils.createWebResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
  }

  @PostMapping(value = { "/role" }, consumes = { Constants.APPLICATION_JSON }, produces = {
      Constants.APPLICATION_JSON })
  public ResponseEntity<?> createRole(@RequestBody Role requestBody) {
	try {
	  roleService.create(requestBody);
	  return WebResponseUtils.createWebResponse(requestBody, HttpStatus.CREATED);
	} catch (Exception e) {
	  e.printStackTrace();
	  return WebResponseUtils.createWebResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
  }

}
