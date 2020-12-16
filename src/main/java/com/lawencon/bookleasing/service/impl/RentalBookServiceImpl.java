package com.lawencon.bookleasing.service.impl;

import com.lawencon.bookleasing.entity.*;
import com.lawencon.bookleasing.repository.*;
import com.lawencon.bookleasing.service.RentalBookService;
import com.lawencon.bookleasing.util.UserSessionCache;

import java.util.List;
import java.util.Optional;

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

	public RentalBookServiceImpl(InventoryRepository inventoryRepository, ProfileRepository profileRepository,
	                             CustomerRepository customerRepository,
	                             RentalHeaderRepository headerRepository, RentalDetailRepository detailRepository,
	                             UserSessionCache userSessionCache) {
		this.inventoryRepository = inventoryRepository;
		this.profileRepository = profileRepository;
		this.customerRepository = customerRepository;
		this.headerRepository = headerRepository;
		this.detailRepository = detailRepository;
		this.userSessionCache = userSessionCache;
	}

	@Override
	public List<Inventory> getInventoryList() throws Exception {
		return Optional.ofNullable(this.inventoryRepository.getInventoryList())
				.orElseThrow();
	}

	@Override
	public Inventory getInventoryByIsbn(String isbn) throws Exception {
		Book book = new Book();
		book.setIsbn(isbn);
		Inventory inventory = new Inventory();
		inventory.setBook(book);
		return Optional.ofNullable(this.inventoryRepository.get(inventory))
				.orElseThrow(() -> new NullPointerException(String.format("There is no book with %s ISBN.", isbn)));
	}

	@Override
	public void addRentalTransaction(RentalHeader header, List<RentalDetail> details) throws Exception {
		Profile profile = Optional.ofNullable(this.profileRepository.add(header.getCustomer().getProfile()))
				.orElseThrow();
		header.getCustomer().setProfile(profile);

		Customer customer = Optional.ofNullable(this.customerRepository.add(header.getCustomer()))
				.orElseThrow(NullPointerException::new);
		header.setCustomer(customer);

		User activeUser = Optional.ofNullable(this.userSessionCache.getActiveUser())
				.orElseThrow(() -> new NullPointerException("There is no active user."));
		header.setUser(activeUser);

		RentalHeader resultHeader = Optional.ofNullable(this.headerRepository.add(header))
				.orElseThrow(() -> new Exception("Failed to add rental header."));

		for (RentalDetail detail : details) {
			detail.setRentalHeader(resultHeader);
			Optional.ofNullable(this.detailRepository.add(detail))
					.orElseThrow(() -> new Exception("Failed to add rental detail."));
		}
	}

}
