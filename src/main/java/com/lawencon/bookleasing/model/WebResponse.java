package com.lawencon.bookleasing.model;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Rian Rivaldo
 */
@JsonInclude(value = Include.NON_NULL)
public class WebResponse<R> {

  private Integer code;
  private HttpStatus status;
  private R result;

  public WebResponse() {
  }

  public WebResponse(R result) {
	this.result = result;
  }

  public WebResponse(HttpStatus status, R result) {
	this.code = status.value();
	this.status = status;
	this.result = result;
  }

  public Integer getCode() {
	return code;
  }

  public void setCode(Integer code) {
	this.code = code;
  }

  public HttpStatus getStatus() {
	return status;
  }

  public void setStatus(HttpStatus status) {
	this.status = status;
  }

  public R getResult() {
	return result;
  }

  public void setResult(R result) {
	this.result = result;
  }

}
