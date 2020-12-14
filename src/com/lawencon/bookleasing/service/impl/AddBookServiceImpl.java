package com.lawencon.bookleasing.service.impl;

import java.util.Optional;

import com.lawencon.bookleasing.model.Author;
import com.lawencon.bookleasing.model.Book;
import com.lawencon.bookleasing.model.BookAuthor;
import com.lawencon.bookleasing.model.BookCategory;
import com.lawencon.bookleasing.model.Category;
import com.lawencon.bookleasing.model.Inventory;
import com.lawencon.bookleasing.model.Language;
import com.lawencon.bookleasing.model.Publisher;
import com.lawencon.bookleasing.service.AddBookService;
import com.lawencon.bookleasing.service.AuthorService;
import com.lawencon.bookleasing.service.BookAuthorService;
import com.lawencon.bookleasing.service.BookCategoryService;
import com.lawencon.bookleasing.service.BookService;
import com.lawencon.bookleasing.service.CategoryService;
import com.lawencon.bookleasing.service.InventoryService;
import com.lawencon.bookleasing.service.LanguageService;
import com.lawencon.bookleasing.service.PublisherService;

/**
 * @author Rian Rivaldo Rumapea
 */
public class AddBookServiceImpl implements AddBookService {

  private PublisherService publisherService = new PublisherServiceImpl();
  private LanguageService languageService = new LanguageServiceImpl();
  private BookService bookService = new BookServiceImpl();
  private AuthorService authorService = new AuthorServiceImpl();
  private CategoryService categoryService = new CategoryServiceImpl();
  private BookAuthorService bookAuthorService = new BookAuthorServiceImpl();
  private BookCategoryService bookCategoryService = new BookCategoryServiceImpl();

  @Override
  public Publisher checkBookPublisher(Publisher publisher) throws Exception {
    Optional.ofNullable(publisher.getCode())
        .orElseThrow(() -> new IllegalArgumentException("Publisher code must be not null!"));
    return Optional.ofNullable(publisherService.get(publisher))
        .orElseThrow(() -> new NullPointerException("Looks like the publisher doesn't exist yet."));
  }

  @Override
  public Publisher addNewPublisher(Publisher newPublisher) throws Exception {
    if (newPublisher.getCode() == null || newPublisher.getName() == null) {
      throw new IllegalArgumentException("Publisher code and name must be not null!");
    }
    return Optional.ofNullable(publisherService.add(newPublisher))
        .orElseThrow();
  }

  @Override
  public Language checkLanguage(Language language) throws Exception {
    Optional.ofNullable(language.getCode())
        .orElseThrow(() -> new IllegalArgumentException("Language code must be not null!"));
    return Optional.ofNullable(languageService.get(language))
        .orElseThrow(() -> new NullPointerException("Looks like the language doesn't exist yet. "));
  }

  @Override
  public Language addNewLanguage(Language newLanguage) throws Exception {
    if (newLanguage.getCode() == null || newLanguage.getName() == null) {
      throw new IllegalArgumentException("Language code and name must be not null!");
    }
    return Optional.ofNullable(languageService.add(newLanguage))
        .orElseThrow();
  }

  @Override
  public Author checkAuthor(Author author) throws Exception {
    Optional.ofNullable(author.getFirstName())
        .orElseThrow(() -> new IllegalArgumentException("Author name must be not null!"));
    Optional.ofNullable(author.getLastName())
        .orElse("");
    return authorService.get(author);
  }

  @Override
  public Author addNewAuthor(Author author) throws Exception {
    return Optional.ofNullable(authorService.add(author))
        .orElseThrow();
  }

  @Override
  public Category checkCategory(Category category) throws Exception {
    Optional.ofNullable(category.getName())
        .orElseThrow(() -> new IllegalArgumentException("Category name must be not null!"));
    return categoryService.get(category);
  }

  @Override
  public Category addNewCategory(Category category) throws Exception {
    Optional.ofNullable(category.getName())
        .orElseThrow(() -> new IllegalArgumentException("Category name must be not null!"));
    return Optional.ofNullable(categoryService.add(category))
        .orElseThrow();
  }

  @Override
  public void addBookAuthor(Author author, Book book) throws Exception {
    if (author == null || book == null) {
      throw new NullPointerException("Author and Book must be not null!");
    }
    BookAuthor bookAuthor = new BookAuthor(author, book);
    BookAuthor selectedBookAuthor = bookAuthorService.get(bookAuthor);
    if (selectedBookAuthor == null || (!author.getId().equals(selectedBookAuthor.getAuthor().getId()))
        && !book.getId().equals(selectedBookAuthor.getBook().getId())) {
      bookAuthorService.add(bookAuthor);
    }
  }

  @Override
  public void addBookCategory(Category category, Book book) throws Exception {
    if (category == null || book == null) {
      throw new NullPointerException("Author and Book must be not null!");
    }
    BookCategory bookCategory = new BookCategory(category, book);
    BookCategory selectedBookCategory = bookCategoryService.get(bookCategory);
    if (selectedBookCategory == null || (!category.getId().equals(selectedBookCategory.getCategory().getId())
        && !book.getId().equals(selectedBookCategory.getBook().getId()))) {
      bookCategoryService.add(bookCategory);
    }
  }

  @Override
  public Book addNewBook(Book newBook) throws Exception {
    Book addedBook = Optional.ofNullable(bookService.add(newBook))
        .orElseThrow();
    InventoryService service = new InventoryServiceImpl();
    Inventory inventory = new Inventory();
    inventory.setBook(addedBook);
    Optional.ofNullable(service.add(inventory))
        .orElseThrow();
    return addedBook;
  }

}
