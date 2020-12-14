package com.lawencon.bookleasing.service;

/**
 * @author Rian Rivaldo Rumapea
 */
public interface BaseDaoService<T> {

  T add(T item) throws Exception;

  T get(T item) throws Exception;
}
