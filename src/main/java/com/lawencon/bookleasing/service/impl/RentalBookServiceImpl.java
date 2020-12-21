package com.lawencon.bookleasing.service.impl;

import java.util.List;

import org.springframework.transaction.support.TransactionTemplate;

import com.lawencon.bookleasing.entity.Book;
import com.lawencon.bookleasing.entity.Customer;
import com.lawencon.bookleasing.entity.Inventory;
import com.lawencon.bookleasing.entity.Profile;
import com.lawencon.bookleasing.entity.RentalDetail;
import com.lawencon.bookleasing.entity.RentalHeader;
import com.lawencon.bookleasing.entity.User;
import com.lawencon.bookleasing.repository.CustomerRepository;
import com.lawencon.bookleasing.repository.InventoryRepository;
import com.lawencon.bookleasing.repository.ProfileRepository;
import com.lawencon.bookleasing.repository.RentalDetailRepository;
import com.lawencon.bookleasing.repository.RentalHeaderRepository;
import com.lawencon.bookleasing.service.RentalBookService;
import com.lawencon.bookleasing.util.UserSessionCache;

/**
 * @author Rian Rivaldo Rumapea
 */
public class RentalBookServiceImpl implements RentalBookService {

  private final InventoryRepository inventoryRepository;
  private final ProfileRepository profileRepository;
  private final CustomerRepository customerRepository;
  private final RentalHeaderRepository headerRepository;
  private final RentalDetailRepository detailRepository;
  private final UserSessionCache userSessionCache;
  private final TransactionTemplate transactionTemplate;

  public RentalBookServiceImpl(InventoryRepository inventoryRepository, ProfileRepository profileRepository,
      CustomerRepository customerRepository,
      RentalHeaderRepository headerRepository, RentalDetailRepository detailRepository,
      UserSessionCache userSessionCache, TransactionTemplate transactionTemplate) {
    this.inventoryRepository = inventoryRepository;
    this.profileRepository = profileRepository;
    this.customerRepository = customerRepository;
    this.headerRepository = headerRepository;
    this.detailRepository = detailRepository;
    this.userSessionCache = userSessionCache;
    this.transactionTemplate = transactionTemplate;
  }

  @Override
  public List<Inventory> getInventoryList() throws Exception {
    return transactionTemplate.execute(val -> {
      try {
        return this.inventoryRepository.getInventoryList();
      } catch (Exception e) {
        e.printStackTrace();
        return null;
      }
    });
  }

  @Override
  public Inventory getInventoryByIsbn(String isbn) throws Exception {
    Book book = new Book();
    book.setIsbn(isbn);
    Inventory inventory = new Inventory();
    inventory.setBook(book);
    return transactionTemplate.execute(val -> {
      try {
        return this.inventoryRepository.get(inventory);
      } catch (Exception e) {
        e.printStackTrace();
        return null;
      }
    });
  }

  @Override
  public void addRentalTransaction(RentalHeader header, List<RentalDetail> details) {
    transactionTemplate.executeWithoutResult(val -> {
      try {
        Profile customerProfile = header.getCustomer().getProfile();
        this.profileRepository.add(customerProfile);
        header.getCustomer().setProfile(customerProfile);

        Customer customer = header.getCustomer();
        this.customerRepository.add(header.getCustomer());
        header.setCustomer(customer);

        User activeUser = this.userSessionCache.getActiveUser();
        header.setUser(activeUser);

        this.headerRepository.add(header);

        for (RentalDetail detail : details) {
          detail.setRentalHeader(header);
          this.detailRepository.add(detail);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    });
  }

}
