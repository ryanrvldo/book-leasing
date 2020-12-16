package com.lawencon.bookleasing.view.cashier;

import com.lawencon.bookleasing.entity.*;
import com.lawencon.bookleasing.service.RentalBookService;
import com.lawencon.bookleasing.util.OnViewFinished;
import com.lawencon.bookleasing.util.ReceiptNumberUtil;
import com.lawencon.bookleasing.view.BaseView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rian Rivaldo Rumapea
 */
public class RentalBookView extends BaseView {

	private final List<RentalDetail> tempRentalList = new ArrayList<>();
	private final RentalBookService service;

	public RentalBookView(RentalBookService service) {
		this.service = service;
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
		String chosenIsbn = readFromInput(String.class);
		if (chosenIsbn == null) {
			onViewFinished.onFinished();
		}

		Inventory chosenInventory;
		try {
			chosenInventory = service.getInventoryByIsbn(chosenIsbn);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}

		System.out.print("Input return days: ");
		Long returnDays = readFromInput(Long.class);
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
		String chosenMenuAddAgain = readFromInput(String.class);

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
		String firstName = readFromInput(String.class);
		System.out.print("Input customer last name: ");
		String lastName = readFromInput(String.class);
		System.out.print("Input email: ");
		String email = readFromInput(String.class);
		System.out.print("Input phone: ");
		String phone = readFromInput(String.class);

		Profile profile = new Profile(firstName, lastName, email, phone);
		Customer customer = new Customer();
		customer.setProfile(profile);
		return customer;
	}

}
