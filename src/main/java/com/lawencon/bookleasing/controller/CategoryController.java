package com.lawencon.bookleasing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.bookleasing.entity.Category;
import com.lawencon.bookleasing.service.CategoryService;
import com.lawencon.bookleasing.util.WebResponseUtils;

/**
 * @author Rian Rivaldo
 */
@RestController
@RequestMapping("/api")
public class CategoryController {

  private final CategoryService categoryService;

  @Autowired
  public CategoryController(CategoryService categoryService) {
	this.categoryService = categoryService;
  }

  @PostMapping(value = { "/category" }, consumes = { "application/json" }, produces = { "application/json" })
  public ResponseEntity<?> createCategory(@RequestBody Category requestBody) {
	try {
	  categoryService.create(requestBody);
	  return WebResponseUtils.createWebResponse(requestBody, HttpStatus.CREATED);
	} catch (Exception e) {
	  e.printStackTrace();
	  return WebResponseUtils.createWebResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
  }

  @GetMapping(value = { "/categories" }, produces = { "application/json" })
  public ResponseEntity<?> getAllCategory() {
	try {
	  return WebResponseUtils.createWebResponse(categoryService.getAll(), HttpStatus.OK);
	} catch (Exception e) {
	  e.printStackTrace();
	  return WebResponseUtils.createWebResponse(e.getMessage(), HttpStatus.NOT_FOUND);
	}
  }

}
