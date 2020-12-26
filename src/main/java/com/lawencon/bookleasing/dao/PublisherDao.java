package com.lawencon.bookleasing.dao;

import com.lawencon.bookleasing.entity.Publisher;

/**
 * @author Rian Rivaldo
 */
public interface PublisherDao extends BaseDao<Publisher> {

  Publisher findByCode(String code) throws Exception;

}
