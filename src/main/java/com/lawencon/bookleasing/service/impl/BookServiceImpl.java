package com.lawencon.bookleasing.service.impl;

import com.lawencon.bookleasing.entity.*;
import com.lawencon.bookleasing.repository.*;
import com.lawencon.bookleasing.service.BookService;

import java.util.Optional;

/**
 * @author Rian Rivaldo Rumapea
 */
public class BookServiceImpl implements BookService {

	private final PublisherRepository publisherRepository;
	private final LanguageRepository languageRepository;
	private final BookRepository bookRepository;
	private final AuthorRepository authorRepository;
	private final CategoryRepository categoryRepository;
	private final BookAuthorRepository bookAuthorRepository;
	private final BookCategoryRepository bookCategoryRepository;
	private final InventoryRepository inventoryRepository;

	public BookServiceImpl(PublisherRepository publisherRepository, LanguageRepository languageRepository,
	                       BookRepository bookRepository, AuthorRepository authorRepository, CategoryRepository categoryRepository,
	                       BookAuthorRepository bookAuthorRepository, BookCategoryRepository bookCategoryRepository,
	                       InventoryRepository inventoryRepository) {
		this.publisherRepository = publisherRepository;
		this.languageRepository = languageRepository;
		this.bookRepository = bookRepository;
		this.authorRepository = authorRepository;
		this.categoryRepository = categoryRepository;
		this.bookAuthorRepository = bookAuthorRepository;
		this.bookCategoryRepository = bookCategoryRepository;
		this.inventoryRepository = inventoryRepository;
	}

	@Override
	public Publisher checkBookPublisher(Publisher publisher) throws Exception {
		Optional.ofNullable(publisher.getCode())
				.orElseThrow(() -> new IllegalArgumentException("Publisher code must be not null!"));
		return Optional.ofNullable(this.publisherRepository.get(publisher))
				.orElseThrow(() -> new NullPointerException("Looks like the publisher doesn't exist yet."));
	}

	@Override
	public Publisher addNewPublisher(Publisher newPublisher) throws Exception {
		if (newPublisher.getCode() == null || newPublisher.getName() == null) {
			throw new IllegalArgumentException("Publisher code and name must be not null!");
		}
		return Optional.ofNullable(this.publisherRepository.add(newPublisher))
				.orElseThrow();
	}

	@Override
	public Language checkLanguage(Language language) throws Exception {
		Optional.ofNullable(language.getCode())
				.orElseThrow(() -> new IllegalArgumentException("Language code must be not null!"));
		return Optional.ofNullable(this.languageRepository.get(language))
				.orElseThrow(() -> new NullPointerException("Looks like the language doesn't exist yet. "));
	}

	@Override
	public Language addNewLanguage(Language newLanguage) throws Exception {
		if (newLanguage.getCode() == null || newLanguage.getName() == null) {
			throw new IllegalArgumentException("Language code and name must be not null!");
		}
		return Optional.ofNullable(this.languageRepository.add(newLanguage))
				.orElseThrow();
	}

	@Override
	public Author checkAuthor(Author author) throws Exception {
		Optional.ofNullable(author.getFirstName())
				.orElseThrow(() -> new IllegalArgumentException("Author name must be not null!"));
		String lastName = Optional.ofNullable(author.getLastName())
				.orElse("");
		author.setLastName(lastName);
		return this.authorRepository.get(author);
	}

	@Override
	public Author addNewAuthor(Author author) throws Exception {
		return Optional.ofNullable(this.authorRepository.add(author))
				.orElseThrow();
	}

	@Override
	public Category checkCategory(Category category) throws Exception {
		Optional.ofNullable(category.getName())
				.orElseThrow(() -> new IllegalArgumentException("Category name must be not null!"));
		return this.categoryRepository.get(category);
	}

	@Override
	public Category addNewCategory(Category category) throws Exception {
		Optional.ofNullable(category.getName())
				.orElseThrow(() -> new IllegalArgumentException("Category name must be not null!"));
		return Optional.ofNullable(this.categoryRepository.add(category))
				.orElseThrow();
	}

	@Override
	public void addBookAuthor(Author author, Book book) throws Exception {
		if (author == null || book == null) {
			throw new NullPointerException("Author and Book must be not null!");
		}
		BookAuthor bookAuthor = new BookAuthor(author, book);
		BookAuthor selectedBookAuthor = this.bookAuthorRepository.get(bookAuthor);
		if (selectedBookAuthor == null || (!author.getId().equals(selectedBookAuthor.getAuthor().getId()))
				&& !book.getId().equals(selectedBookAuthor.getBook().getId())) {
			this.bookAuthorRepository.add(bookAuthor);
		}
	}

	@Override
	public void addBookCategory(Category category, Book book) throws Exception {
		if (category == null || book == null) {
			throw new NullPointerException("Author and Book must be not null!");
		}
		BookCategory bookCategory = new BookCategory(category, book);
		BookCategory selectedBookCategory = this.bookCategoryRepository.get(bookCategory);
		if (selectedBookCategory == null || (!category.getId().equals(selectedBookCategory.getCategory().getId())
				&& !book.getId().equals(selectedBookCategory.getBook().getId()))) {
			this.bookCategoryRepository.add(bookCategory);
		}
	}

	@Override
	public Book addNewBook(Book newBook) throws Exception {
		Book addedBook = Optional.ofNullable(this.bookRepository.add(newBook))
				.orElseThrow();
		Inventory inventory = new Inventory();
		inventory.setBook(addedBook);
		Optional.ofNullable(this.inventoryRepository.add(inventory))
				.orElseThrow();
		return addedBook;
	}

}
