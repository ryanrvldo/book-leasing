package com.lawencon.bookleasing.service.impl;

import java.util.List;
import java.util.Optional;

import com.lawencon.bookleasing.model.Book;
import com.lawencon.bookleasing.model.Customer;
import com.lawencon.bookleasing.model.Inventory;
import com.lawencon.bookleasing.model.Profile;
import com.lawencon.bookleasing.model.RentalDetail;
import com.lawencon.bookleasing.model.RentalHeader;
import com.lawencon.bookleasing.service.CustomerService;
import com.lawencon.bookleasing.service.InventoryService;
import com.lawencon.bookleasing.service.ProfileService;
import com.lawencon.bookleasing.service.RentalBookService;
import com.lawencon.bookleasing.service.RentalDetailService;
import com.lawencon.bookleasing.service.RentalHeaderService;

/**
 * @author Rian Rivaldo Rumapea
 */
public class RentalBookServiceImpl implements RentalBookService {

  private InventoryService inventoryService = new InventoryServiceImpl();

  @Override
  public List<Inventory> getInventoryList() throws Exception {
    return Optional.ofNullable(inventoryService.getInvetoryList())
        .orElseThrow();
  }

  @Override
  public Inventory getInventoryByIsbn(String isbn) throws Exception {
    Book book = new Book();
    book.setIsbn(isbn);
    Inventory inventory = new Inventory();
    inventory.setBook(book);
    return Optional.ofNullable(inventoryService.get(inventory))
        .orElseThrow(() -> new NullPointerException(String.format("There is no book with %s ISBN.", isbn)));
  }

  @Override
  public void addRentalTransaction(RentalHeader header, List<RentalDetail> details) throws Exception {
    ProfileService profileService = new ProfileServiceImpl();
    Profile profile = Optional.ofNullable(profileService.add(header.getCustomer().getProfile()))
        .orElseThrow();
    header.getCustomer().setProfile(profile);

    CustomerService custService = new CustomerServiceImpl();
    Customer customer = Optional.ofNullable(custService.add(header.getCustomer()))
        .orElseThrow(NullPointerException::new);
    header.setCustomer(customer);

    RentalHeaderService headerService = new RentalHeaderServiceImpl();
    RentalHeader resultHeader = Optional.ofNullable(headerService.add(header))
        .orElseThrow(() -> new Exception("Failed to add rental header."));

    RentalDetailService detailService = new RentalDetailServiceImpl();
    for (RentalDetail detail : details) {
      detail.setRentalHeader(resultHeader);
      Optional.ofNullable(detailService.add(detail))
          .orElseThrow(() -> new Exception("Failed to add rental detail."));
    }
  }

}
