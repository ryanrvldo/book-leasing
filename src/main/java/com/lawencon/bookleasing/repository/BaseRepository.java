package com.lawencon.bookleasing.repository;

/**
 * @author Rian Rivaldo Rumapea
 */
public interface BaseRepository<T> {

	T add(T request) throws Exception;

	T get(T request) throws Exception;

}
