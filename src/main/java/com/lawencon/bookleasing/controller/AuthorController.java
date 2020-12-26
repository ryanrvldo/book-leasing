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

import com.lawencon.bookleasing.entity.Author;
import com.lawencon.bookleasing.service.AuthorService;
import com.lawencon.bookleasing.util.WebResponseUtils;

/**
 * @author Rian Rivaldo
 */
@RestController
@RequestMapping(value = "/api")
public class AuthorController {

  private final AuthorService authorService;

  @Autowired
  public AuthorController(AuthorService authorService) {
	this.authorService = authorService;
  }

  @GetMapping(value = { "/author/{id}" })
  public ResponseEntity<?> getAuthorById(@PathVariable("id") Long id) {
	try {
	  Author author = authorService.getAuthorById(id);
	  return WebResponseUtils.createWebResponse(author, HttpStatus.OK);
	} catch (Exception e) {
	  e.printStackTrace();
	  return WebResponseUtils.createWebResponse(e.getMessage(), HttpStatus.NOT_FOUND);
	}
  }

  @GetMapping(value = { "/author" })
  public ResponseEntity<?> getAuthorByName(@RequestParam("first_name") String firstName,
      @RequestParam("last_name") String lastName) {
	try {
	  Author author = authorService.getAuthorByFirstAndLastName(firstName, lastName);
	  return WebResponseUtils.createWebResponse(author, HttpStatus.OK);
	} catch (Exception e) {
	  e.printStackTrace();
	  return WebResponseUtils.createWebResponse(e.getMessage(), HttpStatus.NOT_FOUND);
	}
  }

  @GetMapping(value = "/authors", produces = { "application/json" })
  public ResponseEntity<?> getAllAuthor() {
	try {
	  return WebResponseUtils.createWebResponse(authorService.getAll(), HttpStatus.OK);
	} catch (Exception e) {
	  e.printStackTrace();
	  return WebResponseUtils.createWebResponse(e.getMessage(), HttpStatus.NO_CONTENT);
	}
  }

  @PostMapping(value = "/author", consumes = { "application/json" }, produces = { "application/json" })
  public ResponseEntity<?> createAuthor(@RequestBody Author requestBody) {
	try {
	  authorService.create(requestBody);
	  return WebResponseUtils.createWebResponse(requestBody, HttpStatus.CREATED);
	} catch (Exception e) {
	  e.printStackTrace();
	  return WebResponseUtils.createWebResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
  }

//  @PostMapping(value = "/author-book", consumes = "application/json", produces = "application/json")
//  public ResponseEntity<?> createBookAuthor(@RequestBody BookAuthor requestBody) {
//	try {
//	  authorService.createBookAuthor(requestBody);
//	  return WebResponseUtils.createWebResponse(requestBody, HttpStatus.CREATED);
//	} catch (Exception e) {
//	  e.printStackTrace();
//	  return WebResponseUtils.createWebResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//	}
//  }

  @DeleteMapping(value = { "/author/{id}" })
  public ResponseEntity<?> deleteAuthorById(@PathVariable("id") Long id) {
	try {
	  authorService.deleteById(id);
	  String result = String.format("Author with id: %d has been successfully deleted.", id);
	  return WebResponseUtils.createWebResponse(result, HttpStatus.OK);
	} catch (Exception e) {
	  e.printStackTrace();
	  return WebResponseUtils.createWebResponse(e.getMessage(), HttpStatus.NOT_FOUND);
	}
  }

}
