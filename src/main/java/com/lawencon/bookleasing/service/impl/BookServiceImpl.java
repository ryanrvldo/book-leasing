package com.lawencon.bookleasing.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.bookleasing.dao.BookDao;
import com.lawencon.bookleasing.entity.Author;
import com.lawencon.bookleasing.entity.Book;
import com.lawencon.bookleasing.entity.Category;
import com.lawencon.bookleasing.entity.Inventory;
import com.lawencon.bookleasing.entity.InventoryStatus;
import com.lawencon.bookleasing.entity.Language;
import com.lawencon.bookleasing.entity.Publisher;
import com.lawencon.bookleasing.error.DataAlreadyExistsException;
import com.lawencon.bookleasing.error.DataIsNotExistsException;
import com.lawencon.bookleasing.model.BookDetails;
import com.lawencon.bookleasing.service.AuthorService;
import com.lawencon.bookleasing.service.BookService;
import com.lawencon.bookleasing.service.CategoryService;
import com.lawencon.bookleasing.service.InventoryService;
import com.lawencon.bookleasing.service.InventoryStatusService;
import com.lawencon.bookleasing.service.LanguageService;
import com.lawencon.bookleasing.service.PublisherService;
import com.lawencon.bookleasing.util.ThrowableSupplier;

/**
 * @author Rian Rivaldo
 */
@Service
public class BookServiceImpl implements BookService {

  private final BookDao dao;
  private final LanguageService languageService;
  private final PublisherService publisherService;
  private final AuthorService authorService;
  private final CategoryService categoryService;
  private final InventoryService inventoryService;
  private final InventoryStatusService statusService;

  @Autowired
  public BookServiceImpl(@Qualifier("book-jpa") BookDao dao, LanguageService languageService,
      PublisherService publisherService, AuthorService authorService, CategoryService categoryService,
      InventoryService inventoryService, InventoryStatusService statusService) {
	this.dao = dao;
	this.languageService = languageService;
	this.publisherService = publisherService;
	this.authorService = authorService;
	this.categoryService = categoryService;
	this.inventoryService = inventoryService;
	this.statusService = statusService;
  }

  @Transactional
  @Override
  public void createBookWithDetails(BookDetails bookDetails) throws Exception {
	Book book = bookDetails.getBook();
	this.create(book);
	authorService.createBookAuthor(book, bookDetails.getAuthors());
	categoryService.createBookCategory(book, bookDetails.getCategories());

	int stock = bookDetails.getStock();
	for (int i = 0; i < stock; i++) {
	  Inventory inventory = new Inventory();
	  inventory.setBook(book);
	  InventoryStatus status = statusService.getByStatus(bookDetails.getStatus().getStatus());
	  if (status == null) {
		statusService.create(bookDetails.getStatus());
		inventory.setStatus(bookDetails.getStatus());
	  } else {
		inventory.setStatus(status);
	  }
	  inventoryService.create(inventory);
	}

  }

  @Transactional
  @Override
  public void create(Book data) throws Exception {
	Book book = validateGet(() -> this.dao.findByIsbn(data.getIsbn()));
	if (book != null) {
	  throw new DataAlreadyExistsException(data.getIsbn());
	}

	Publisher publisher = publisherService.getPublisherByCode(data.getPublisher().getCode());
	if (publisher == null) {
	  this.publisherService.create(data.getPublisher());
	} else {
	  data.setPublisher(publisher);
	}

	Language language = languageService.getLanguageByCode(data.getLanguage().getCode());
	if (language == null) {
	  this.languageService.create(data.getLanguage());
	} else {
	  data.setLanguage(language);
	}

	this.dao.insert(data);
  }

  @Override
  public BookDetails getBookByIsbn(String isbn) throws Exception {
	return getBook(() -> dao.findByIsbn(isbn));
  }

  @Override
  public BookDetails getBookById(Long id) throws Exception {
	return getBook(() -> dao.findById(id));
  }

  @Override
  public void update(Book data) throws Exception {
	dao.update(data);
  }

  @Override
  public void delete(Book data) throws Exception {
	dao.delete(data);
  }

  @Override
  public List<Book> getAll() throws Exception {
	List<Book> bookList = dao.findAll();
	if (bookList.isEmpty()) {
	  throw new Exception("Book is empty.");
	}
	return bookList;
  }

  private BookDetails getBook(ThrowableSupplier<Book> supplier) throws Exception {
	BookDetails bookDetails = new BookDetails();
	Book book = Optional.ofNullable(validateGet(supplier))
	    .orElseThrow(() -> new DataIsNotExistsException());
	bookDetails.setBook(book);

	List<Author> authorList = authorService.getAllAuthorByBook(book);
	if (!authorList.isEmpty()) {
	  bookDetails.setAuthors(authorList);
	}

	List<Category> categoryList = categoryService.getAllCategoryByBook(book);
	if (!categoryList.isEmpty()) {
	  bookDetails.setCategories(categoryList);
	}
	return bookDetails;
  }

}
