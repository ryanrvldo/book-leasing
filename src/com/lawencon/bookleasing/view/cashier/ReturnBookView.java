package com.lawencon.bookleasing.view.cashier;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.lawencon.bookleasing.model.RentalHeader;
import com.lawencon.bookleasing.model.Return;
import com.lawencon.bookleasing.model.User;
import com.lawencon.bookleasing.service.ReturnBookService;
import com.lawencon.bookleasing.service.impl.ReturnBookServiceImpl;
import com.lawencon.bookleasing.util.OnViewFinished;
import com.lawencon.bookleasing.view.BaseView;

/**
 * @author Rian Rivaldo Rumapea
 */
public class ReturnBookView extends BaseView {

  private User user;

  private ReturnBookService service = new ReturnBookServiceImpl();

  public ReturnBookView(User user) {
    this.user = user;
  }

  @Override
  public void show(OnViewFinished onViewFinished) {
    String mainMenu = buildMenusOf(
        "\n=============== Return Book ===============\n",
        "Input receipt number: ");
    System.out.print(mainMenu);
    String chosenReceipt = getInputString();
    RentalHeader rentalHeader = new RentalHeader();
    rentalHeader.setReceipt(chosenReceipt);
    Return returnData = new Return();
    try {
      returnData = service.checkReceiptNumber(rentalHeader);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
      onViewFinished.onFinished();
    }

    BigDecimal totalPrice = BigDecimal.ZERO;
    try {
      totalPrice = service.getTotalRentalCost(chosenReceipt);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
      onViewFinished.onFinished();
    }
    returnData.setReturnedAt(LocalDateTime.now());
    returnData.setUser(user);
    returnData.setTotalPrice(totalPrice);

    try {
      service.addReturnBook(rentalHeader, returnData);
      System.out.printf("Total price = %.2f%n", totalPrice.doubleValue());
      System.out.println("Success book have been returned! Thank you.");
    } catch (Exception e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
    }
    onViewFinished.onFinished();
  }

}
