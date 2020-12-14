package com.lawencon.bookleasing.service.impl;

import com.lawencon.bookleasing.dao.PublisherDao;
import com.lawencon.bookleasing.dao.impl.PublisherDaoImpl;
import com.lawencon.bookleasing.model.Publisher;
import com.lawencon.bookleasing.service.PublisherService;

/**
 * @author Rian Rivaldo Rumapea
 */
public class PublisherServiceImpl implements PublisherService {

  private PublisherDao dao = new PublisherDaoImpl();

  @Override
  public Publisher add(Publisher newPublisher) throws Exception {
    return dao.insert(newPublisher);
  }

  @Override
  public Publisher get(Publisher publisher) throws Exception {
    return dao.get(publisher);
  }

}
