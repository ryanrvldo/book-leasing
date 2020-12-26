package com.lawencon.bookleasing.util;

/**
 * @author Rian Rivaldo
 */
@FunctionalInterface
public interface ThrowableVoidCallback {

  void run() throws Exception;

}
