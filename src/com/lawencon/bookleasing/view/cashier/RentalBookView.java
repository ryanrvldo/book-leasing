package com.lawencon.bookleasing.view.cashier;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.lawencon.bookleasing.model.Book;
import com.lawencon.bookleasing.model.Customer;
import com.lawencon.bookleasing.model.Inventory;
import com.lawencon.bookleasing.model.Profile;
import com.lawencon.bookleasing.model.RentalDetail;
import com.lawencon.bookleasing.model.RentalHeader;
import com.lawencon.bookleasing.model.User;
import com.lawencon.bookleasing.service.RentalBookService;
import com.lawencon.bookleasing.service.impl.RentalBookServiceImpl;
import com.lawencon.bookleasing.util.OnViewFinished;
import com.lawencon.bookleasing.util.ReceiptNumberUtil;
import com.lawencon.bookleasing.view.BaseView;

/**
 * @author Rian Rivaldo Rumapea
 */
public class RentalBookView extends BaseView {

  private User user;
  private RentalBookService service = new RentalBookServiceImpl();
  private List<RentalDetail> tempRentalList;

  public RentalBookView(User user) {
    this.user = user;
    this.tempRentalList = new ArrayList<>();
  }

  @Override
  public void show(OnViewFinished onViewFinished) {
    System.out.println("\n=============== Rental Book ===============\n");
    addBook(() -> show(onViewFinished));
    onViewFinished.onFinished();
  }

  public void addBook(OnViewFinished onViewFinished) {
    List<Inventory> inventoryList = new ArrayList<>();
    try {
      inventoryList = service.getInventoryList();
    } catch (Exception e) {
      System.out.printf("Failed to get inventory list: %s", e.getMessage());
      onViewFinished.onFinished();
    }

    System.out.println("\nList Book:");
    System.out.println("--------------------------------------------------------------------");
    System.out.printf("| %-20s | %-5s | %-20s | %-10s |%n", "Title", "Year", "ISBN", "Cost");
    System.out.println("--------------------------------------------------------------------");
    inventoryList.forEach(inventory -> {
      Book book = inventory.getBook();
      if (book.getTitle().length() > 20) {
        book.setTitle(book.getTitle().substring(0, 19));
      }
      System.out.printf("| %-20s | %-5d | %-20s | %-10.2f |%n", book.getTitle(), book.getPublicationYear(),
          book.getIsbn(), book.getRentalCost().doubleValue());
    });
    System.out.println("--------------------------------------------------------------------");

    System.out.print("Input isbn: ");
    String chosenIsbn = getInputString();
    if (chosenIsbn == null) {
      onViewFinished.onFinished();
    }

    Inventory chosenInventory = null;
    try {
      chosenInventory = service.getInventoryByIsbn(chosenIsbn);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return;
    }

    System.out.print("Input return days: ");
    long returnDays = getInputInteger();
    if (returnDays == -1) {
      onViewFinished.onFinished();
    }

    LocalDateTime rentalDateTime = LocalDateTime.now();
    LocalDateTime returnDateTime = rentalDateTime.plusDays(returnDays);

    RentalDetail rentalDetail = new RentalDetail();
    rentalDetail.setInventory(chosenInventory);
    rentalDetail.setTotalPrice(chosenInventory.getBook().getRentalCost());
    rentalDetail.setRentalDate(rentalDateTime);
    rentalDetail.setReturnDate(returnDateTime);

    System.out.print("Want to add again [y/n]? ");
    String chosenMenuAddAgain = getInputString();

    if (chosenMenuAddAgain.equalsIgnoreCase("y")) {
      tempRentalList.add(rentalDetail);
    } else if (chosenMenuAddAgain.equalsIgnoreCase("n")) {
      tempRentalList.add(rentalDetail);
      addTransactionToDatabase();
      return;
    } else {
      showInvalidInputMessage();
    }
    onViewFinished.onFinished();
  }

  private void addTransactionToDatabase() {
    Customer customer = getInputtedCustomer();
    RentalHeader header = new RentalHeader();
    header.setReceipt(ReceiptNumberUtil.getReceiptNumber());
    header.setCustomer(customer);
    header.setUser(user);
    try {
      if (tempRentalList.isEmpty()) throw new NullPointerException("Rental list is empty.");
      service.addRentalTransaction(header, tempRentalList);
      System.out.printf("Success added to transaction! Receipt Number: %s", header.getReceipt());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private Customer getInputtedCustomer() {
    System.out.print("Input customer first name: ");
    String custFirstName = getInputString();
    System.out.print("Input customer last name: ");
    String custLastName = getInputString();
    System.out.print("Input email: ");
    String custEmail = getInputString();
    System.out.print("Input phone: ");
    String custPhone = getInputString();

    Profile profile = new Profile(custFirstName, custLastName, custEmail, custPhone);
    Customer customer = new Customer();
    customer.setProfile(profile);
    return customer;
  }

}
