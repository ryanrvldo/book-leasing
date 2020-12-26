package com.lawencon.bookleasing.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.lawencon.bookleasing.model.WebResponse;

/**
 * @author Rian Rivaldo
 */

public class WebResponseUtils {

  public static <R> ResponseEntity<WebResponse<R>> createWebResponse(R result, HttpStatus status) {
	WebResponse<R> webResponse = new WebResponse<R>(result);
	return new ResponseEntity<>(webResponse, status);
  }

}
