package com.lawencon.bookleasing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.bookleasing.constants.Constants;
import com.lawencon.bookleasing.entity.User;
import com.lawencon.bookleasing.service.UserService;
import com.lawencon.bookleasing.util.WebResponseUtils;

/**
 * @author Rian Rivaldo
 */
@RestController
@RequestMapping(value = { Constants.BASE_ENDPOINT })
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
	this.userService = userService;
  }

  @GetMapping(value = { "/user/{username}" }, produces = { Constants.APPLICATION_JSON })
  public ResponseEntity<?> getUserByUsername(@PathVariable("username") String username) {
	try {
	  return WebResponseUtils.createWebResponse(userService.getByUsername(username), HttpStatus.OK);
	} catch (Exception e) {
	  e.printStackTrace();
	  return WebResponseUtils.createWebResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
  }

  @GetMapping(value = { "/user" }, produces = { Constants.APPLICATION_JSON })
  public ResponseEntity<?> getUserByUsername(@RequestParam("id") Long id) {
	try {
	  return WebResponseUtils.createWebResponse(userService.getById(id), HttpStatus.OK);
	} catch (Exception e) {
	  e.printStackTrace();
	  return WebResponseUtils.createWebResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
  }

  @PostMapping(value = { "/user" }, consumes = { Constants.APPLICATION_JSON }, produces = {
      Constants.APPLICATION_JSON })
  public ResponseEntity<?> createUser(@RequestBody User requestBody) {
	try {
	  userService.create(requestBody);
	  return WebResponseUtils.createWebResponse(requestBody, HttpStatus.CREATED);
	} catch (Exception e) {
	  e.printStackTrace();
	  return WebResponseUtils.createWebResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
  }

  @DeleteMapping(value = { "/user" })
  public ResponseEntity<?> deleteUserById(@RequestParam("id") Long id) {
	try {
	  userService.deleteById(id);
	  return WebResponseUtils.createWebResponse("Deleted user with Id : " + id, HttpStatus.OK);
	} catch (Exception e) {
	  e.printStackTrace();
	  return WebResponseUtils.createWebResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
  }

}
