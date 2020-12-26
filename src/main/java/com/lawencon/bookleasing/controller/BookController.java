package com.lawencon.bookleasing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.bookleasing.constants.Constants;
import com.lawencon.bookleasing.entity.Book;
import com.lawencon.bookleasing.model.BookDetails;
import com.lawencon.bookleasing.service.BookService;
import com.lawencon.bookleasing.util.WebResponseUtils;

/**
 * @author Rian Rivaldo
 */

@RestController
@RequestMapping(value = { Constants.BASE_ENDPOINT })
public class BookController {

  private final BookService bookService;

  @Autowired
  public BookController(BookService bookService) {
	this.bookService = bookService;
  }

  @GetMapping(value = { "/book/{isbn}" })
  public ResponseEntity<?> getBookByIsbn(@PathVariable("isbn") String isbn) {
	try {
	  BookDetails response = bookService.getBookByIsbn(isbn);
	  return WebResponseUtils.createWebResponse(response, HttpStatus.OK);
	} catch (Exception e) {
	  e.printStackTrace();
	  return WebResponseUtils.createWebResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
  }

  @GetMapping(value = { "/book" })
  public ResponseEntity<?> getBookById(@RequestParam("id") Long id) {
	try {
	  BookDetails response = bookService.getBookById(id);
	  return WebResponseUtils.createWebResponse(response, HttpStatus.OK);
	} catch (Exception e) {
	  e.printStackTrace();
	  return WebResponseUtils.createWebResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
  }

  @GetMapping(value = { "/books" })
  public ResponseEntity<?> getAllBook() {
	try {
	  List<Book> response = bookService.getAll();
	  return WebResponseUtils.createWebResponse(response, HttpStatus.OK);
	} catch (Exception e) {
	  e.printStackTrace();
	  return WebResponseUtils.createWebResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
  }

  @PostMapping(value = { "/book" }, consumes = { "application/json" }, produces = { "application/json" })
  public ResponseEntity<?> createBook(@RequestBody BookDetails requestBody) {
	try {
	  bookService.createBookWithDetails(requestBody);
	  return WebResponseUtils.createWebResponse(requestBody, HttpStatus.CREATED);
	} catch (Exception e) {
	  e.printStackTrace();
	  return WebResponseUtils.createWebResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
  }

}
