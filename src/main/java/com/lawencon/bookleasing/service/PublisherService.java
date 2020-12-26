package com.lawencon.bookleasing.service;

import com.lawencon.bookleasing.entity.Publisher;

/**
 * @author Rian Rivaldo Rumapea
 */
public interface PublisherService extends BaseService<Publisher> {

  Publisher getPublisherByCode(String code) throws Exception;

}
