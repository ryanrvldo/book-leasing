package com.lawencon.bookleasing.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lawencon.bookleasing.dao.CustomerDao;
import com.lawencon.bookleasing.entity.Customer;
import com.lawencon.bookleasing.entity.Profile;
import com.lawencon.bookleasing.error.DataIsNotExistsException;
import com.lawencon.bookleasing.service.CustomerService;
import com.lawencon.bookleasing.service.ProfileService;

/**
 * @author Rian Rivaldo Rumapea
 */
@Service
public class CustomerServiceImpl implements CustomerService {

  private final CustomerDao dao;
  private final ProfileService profileService;

  @Autowired
  public CustomerServiceImpl(@Qualifier("customer-hql") CustomerDao dao,
      ProfileService profileService) {
    this.dao = dao;
    this.profileService = profileService;
  }

  @Transactional
  @Override
  public void create(Customer data) throws Exception {
    Objects.requireNonNull(data, () -> "Customer data must be submitted");
    Objects.requireNonNull(data.getProfile(), "Profile data must be submitted.");
    Objects.requireNonNull(data.getProfile().getFullName(),
        () -> "Full name data must be submitted.");
    Objects.requireNonNull(data.getProfile().getEmail(), () -> "Email data must be submitted");
    Objects.requireNonNull(data.getProfile().getPhone(), () -> "Phone data must be submitted");
    Profile profile = profileService.getProfileByEmailAndPhone(data.getProfile().getEmail(),
        data.getProfile().getPhone());
    if (profile == null) {
      profileService.create(data.getProfile());
    } else {
      data.setProfile(profile);
    }
    data.setCreatedDate(LocalDateTime.now());
    this.dao.insert(data);
  }

  @Override
  public Customer getCustomerByEmail(String email) throws Exception {
    return Optional.ofNullable(validateGet(() -> this.dao.findByEmail(email)))
        .orElseThrow(() -> new DataIsNotExistsException(email));
  }

  @Override
  public void update(Customer data) throws Exception {
    Customer customer = dao.findById(data.getId());
    Optional.ofNullable(customer).orElseThrow();
    dao.update(data);
  }

  @Override
  public void delete(Customer data) throws Exception {
    dao.delete(data);
  }

  @Override
  public List<Customer> getAll() throws Exception {
    List<Customer> customerList = dao.findAll();
    if (customerList.isEmpty())
      throw new NoResultException("Customer list is empty.");
    return customerList;
  }

}
