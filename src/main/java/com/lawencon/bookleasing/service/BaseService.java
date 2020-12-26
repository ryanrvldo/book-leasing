package com.lawencon.bookleasing.service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

import javax.persistence.NoResultException;

import com.lawencon.bookleasing.util.ThrowableSupplier;

/**
 * @author Rian Rivaldo Rumapea
 */
public interface BaseService<T> {

  void create(T data) throws Exception;

  void update(T data) throws Exception;

  void delete(T data) throws Exception;

  List<T> getAll() throws Exception;

  default T validateGet(ThrowableSupplier<T> supplier) throws Exception {
	try {
	  return supplier.get();
	} catch (Exception e) {
	  if (e instanceof NoResultException) {
		return null;
	  }
	  throw new Exception(e);
	}
  }

  default Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
	Set<Object> seen = ConcurrentHashMap.newKeySet();
	return t -> seen.add(keyExtractor.apply(t));
  }

}
