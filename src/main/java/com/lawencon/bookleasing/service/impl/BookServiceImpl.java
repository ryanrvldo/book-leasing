package com.lawencon.bookleasing.service.impl;

import java.util.Optional;

import org.springframework.transaction.support.TransactionTemplate;

import com.lawencon.bookleasing.entity.Author;
import com.lawencon.bookleasing.entity.Book;
import com.lawencon.bookleasing.entity.BookAuthor;
import com.lawencon.bookleasing.entity.BookCategory;
import com.lawencon.bookleasing.entity.Category;
import com.lawencon.bookleasing.entity.Inventory;
import com.lawencon.bookleasing.entity.Language;
import com.lawencon.bookleasing.entity.Publisher;
import com.lawencon.bookleasing.repository.AuthorRepository;
import com.lawencon.bookleasing.repository.BookAuthorRepository;
import com.lawencon.bookleasing.repository.BookCategoryRepository;
import com.lawencon.bookleasing.repository.BookRepository;
import com.lawencon.bookleasing.repository.CategoryRepository;
import com.lawencon.bookleasing.repository.InventoryRepository;
import com.lawencon.bookleasing.repository.LanguageRepository;
import com.lawencon.bookleasing.repository.PublisherRepository;
import com.lawencon.bookleasing.service.BookService;

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
  private final TransactionTemplate transactionTemplate;

  public BookServiceImpl(PublisherRepository publisherRepository, LanguageRepository languageRepository,
      BookRepository bookRepository, AuthorRepository authorRepository, CategoryRepository categoryRepository,
      BookAuthorRepository bookAuthorRepository, BookCategoryRepository bookCategoryRepository,
      InventoryRepository inventoryRepository, TransactionTemplate transactionTemplate) {
    this.publisherRepository = publisherRepository;
    this.languageRepository = languageRepository;
    this.bookRepository = bookRepository;
    this.authorRepository = authorRepository;
    this.categoryRepository = categoryRepository;
    this.bookAuthorRepository = bookAuthorRepository;
    this.bookCategoryRepository = bookCategoryRepository;
    this.inventoryRepository = inventoryRepository;
    this.transactionTemplate = transactionTemplate;
  }

  @Override
  public Publisher checkBookPublisher(Publisher publisher) throws Exception {
    Optional.ofNullable(publisher.getCode())
        .orElseThrow(() -> new IllegalArgumentException("Publisher code must be not null!"));
    return this.transactionTemplate.execute(val -> {
      try {
        return this.publisherRepository.get(publisher);
      } catch (Exception e) {
        throw new RuntimeException("Looks like the publisher doesn't exist yet.");
      }
    });
  }

  @Override
  public void addNewPublisher(Publisher newPublisher) throws Exception {
    if (newPublisher.getCode() == null || newPublisher.getName() == null) {
      throw new IllegalArgumentException("Publisher code and name must be not null!");
    }
    this.transactionTemplate.executeWithoutResult(val -> {
      try {
        this.publisherRepository.add(newPublisher);
      } catch (Exception e) {
        throw new RuntimeException();
      }
    });
  }

  @Override
  public Language checkLanguage(Language language) throws Exception {
    Optional.ofNullable(language.getCode())
        .orElseThrow(() -> new IllegalArgumentException("Language code must be not null!"));
    return transactionTemplate.execute(val -> {
      try {
        return this.languageRepository.get(language);
      } catch (Exception e) {
        throw new RuntimeException("Looks like the language doesn't exist yet. ");
      }
    });
  }

  @Override
  public void addNewLanguage(Language newLanguage) throws Exception {
    if (newLanguage.getCode() == null || newLanguage.getName() == null) {
      throw new IllegalArgumentException("Language code and name must be not null!");
    }
    transactionTemplate.executeWithoutResult(val -> {
      try {
        this.languageRepository.add(newLanguage);
      } catch (Exception e) {
        throw new RuntimeException();
      }
    });
  }

  @Override
  public Author checkAuthor(Author author) throws Exception {
    Optional.ofNullable(author.getFirstName())
        .orElseThrow(() -> new IllegalArgumentException("Author name must be not null!"));
    String lastName = Optional.ofNullable(author.getLastName())
        .orElse("");
    author.setLastName(lastName);
    return this.transactionTemplate.execute(val -> {
      try {
        return this.authorRepository.get(author);
      } catch (Exception e) {
        throw new RuntimeException();
      }
    });
  }

  @Override
  public void addNewAuthor(Author author) throws Exception {
    this.transactionTemplate.executeWithoutResult(val -> {
      try {
        this.authorRepository.add(author);
      } catch (Exception e) {
        throw new RuntimeException(e.getMessage());
      }
    });
  }

  @Override
  public Category checkCategory(Category category) throws Exception {
    Optional.ofNullable(category.getName())
        .orElseThrow(() -> new IllegalArgumentException("Category name must be not null!"));
    return this.transactionTemplate.execute(val -> {
      try {
        return this.categoryRepository.get(category);
      } catch (Exception e) {
        throw new RuntimeException(e.getMessage());
      }
    });
  }

  @Override
  public void addNewCategory(Category category) throws Exception {
    Optional.ofNullable(category.getName())
        .orElseThrow(() -> new IllegalArgumentException("Category name must be not null!"));
    this.transactionTemplate.executeWithoutResult(val -> {
      try {
        this.categoryRepository.add(category);
      } catch (Exception e) {
        throw new RuntimeException(e.getMessage());
      }
    });
  }

  @Override
  public void addBookAuthor(Author author, Book book) throws Exception {
    if (author == null || book == null) {
      throw new NullPointerException("Author and Book must be not null!");
    }
    BookAuthor bookAuthor = new BookAuthor(author, book);
    this.transactionTemplate.executeWithoutResult(val -> {
      try {
        BookAuthor selectedBookAuthor = this.bookAuthorRepository.get(bookAuthor);
        if (selectedBookAuthor == null || (!author.getId().equals(selectedBookAuthor.getAuthor().getId()))
            && !book.getId().equals(selectedBookAuthor.getBook().getId())) {
          this.bookAuthorRepository.add(bookAuthor);
        }
      } catch (Exception e) {
        throw new RuntimeException(e.getMessage());
      }
    });
  }

  @Override
  public void addBookCategory(Category category, Book book) throws Exception {
    if (category == null || book == null) {
      throw new NullPointerException("Author and Book must be not null!");
    }
    BookCategory bookCategory = new BookCategory(category, book);
    this.transactionTemplate.executeWithoutResult(val -> {
      try {
        BookCategory selectedBookCategory = this.bookCategoryRepository.get(bookCategory);
        if (selectedBookCategory == null || (!category.getId().equals(selectedBookCategory.getCategory().getId())
            && !book.getId().equals(selectedBookCategory.getBook().getId()))) {
          this.bookCategoryRepository.add(bookCategory);
        }
      } catch (Exception e) {
        throw new RuntimeException(e.getMessage());
      }
    });
  }

  @Override
  public void addNewBook(Book newBook) throws Exception {
    this.transactionTemplate.executeWithoutResult(val -> {
      try {
        this.bookRepository.add(newBook);
        Inventory inventory = new Inventory();
        inventory.setBook(newBook);
        this.inventoryRepository.add(inventory);
      } catch (Exception e) {
        throw new RuntimeException(e.getMessage());
      }
    });
  }

}
