package com.lawencon.bookleasing.view.cashier;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.lawencon.bookleasing.entity.RentalHeader;
import com.lawencon.bookleasing.entity.Return;
import com.lawencon.bookleasing.service.ReturnBookService;
import com.lawencon.bookleasing.util.OnViewFinished;
import com.lawencon.bookleasing.view.BaseView;

/**
 * @author Rian Rivaldo Rumapea
 */
public class ReturnBookView extends BaseView {

  private final ReturnBookService service;

  public ReturnBookView(ReturnBookService service) {
    this.service = service;
  }

  @Override
  public void show(OnViewFinished onViewFinished) {
    String mainMenu = buildMenusOf(
        "\n=============== Return Book ===============\n",
        "Input receipt number: ");
    System.out.print(mainMenu);
    String chosenReceipt = readFromInput(String.class);
    RentalHeader rentalHeader = new RentalHeader();
    rentalHeader.setReceipt(chosenReceipt);
    Return returnData = new Return();
    try {
      returnData = service.checkReceiptNumber(rentalHeader);
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println(e.getMessage());
      onViewFinished.onFinished();
    }

    BigDecimal totalPrice = BigDecimal.ZERO;
    try {
      totalPrice = service.getTotalRentalCost(chosenReceipt);
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println(e.getMessage());
      onViewFinished.onFinished();
    }
    returnData.setReturnedAt(LocalDateTime.now());
    returnData.setTotalPrice(totalPrice);

    try {
      service.addReturnBook(rentalHeader, returnData);
      System.out.printf("Total price = %.2f%n", totalPrice.doubleValue());
      System.out.println("Success book have been returned! Thank you.");
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println(e.getMessage());
    }
    onViewFinished.onFinished();
  }

}
