package com.lawencon.bookleasing.view.admin;

import java.math.BigDecimal;
import java.util.Optional;

import com.lawencon.bookleasing.model.Author;
import com.lawencon.bookleasing.model.Book;
import com.lawencon.bookleasing.model.Category;
import com.lawencon.bookleasing.model.Language;
import com.lawencon.bookleasing.model.Publisher;
import com.lawencon.bookleasing.service.AddBookService;
import com.lawencon.bookleasing.service.impl.AddBookServiceImpl;
import com.lawencon.bookleasing.util.OnViewFinished;
import com.lawencon.bookleasing.view.BaseView;

/**
 * @author Rian Rivaldo Rumapea
 */
public class AddBookView extends BaseView {

  private AddBookService service = new AddBookServiceImpl();

  @Override
  public void show(OnViewFinished onViewFinished) {
    System.out.println("\n=============== Add Book ===============\n");

    System.out.print("Input title: ");
    String title = getInputString();
    System.out.print("Input ISBN: ");
    String isbn = getInputString();
    System.out.print("Input publication year: ");
    short year = getInputShort();
    System.out.print("Input rental cost: ");
    BigDecimal rentalCost = new BigDecimal(getInputString());
    System.out.print("Input replacement cost: ");
    BigDecimal replacementCost = new BigDecimal(getInputString());

    Book book = new Book(title, null, isbn, null, rentalCost, replacementCost, year);
    try {
      Publisher publisher = Optional.ofNullable(checkBookPublisher())
          .orElseGet(() -> addNewPublisher());
      Language language = Optional.ofNullable(checkBookLanguage())
          .orElseGet(() -> addNewBookLanguage());
      book.setPublisher(publisher);
      book.setLanguage(language);

      Book addedBook = service.addNewBook(book);
      System.out.printf("%nSuccess added %s to the book list.%nNow add book author and category.%n%n", title);
      addBookAuthor(addedBook);
      addBookCategory(addedBook);
      System.out.println("Success! Thank you.");
      onViewFinished.onFinished();
    } catch (Exception e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
      show(onViewFinished);
    }
  }

  private Publisher checkBookPublisher() {
    System.out.print("Input publisher code: ");
    String publisherCode = getInputString();
    Publisher publisher = new Publisher();
    publisher.setCode(publisherCode);
    try {
      return service.checkBookPublisher(publisher);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return null;
  }

  private Publisher addNewPublisher() {
    System.out.print("\nInput publisher name: ");
    String name = getInputString();
    System.out.print("Input publisher code: ");
    String code = getInputString();
    System.out.print("Input publisher city: ");
    String city = getInputString();

    Publisher publisher = new Publisher(code, name, city);
    try {
      return service.addNewPublisher(publisher);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
    }
    return null;
  }

  private Language checkBookLanguage() {
    System.out.print("Input language code: ");
    String languageCode = getInputString();
    try {
      return service.checkLanguage(new Language(languageCode));
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return null;
  }

  private Language addNewBookLanguage() {
    System.out.print("\nInput language name: ");
    String name = getInputString();
    System.out.print("Input language code: ");
    String code = getInputString();
    try {
      return service.addNewLanguage(new Language(code, name));
    } catch (Exception e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
    }
    return null;
  }

  private void addBookAuthor(Book book) {
    System.out.print("Input author first name: ");
    String firstName = getInputString();
    System.out.print("Input author last name: ");
    String lastName = getInputString();
    Author author = new Author(null, firstName, lastName);
    try {
      Author addedAuthor = service.checkAuthor(author);
      if (addedAuthor == null || addedAuthor.getId() == null) {
        addedAuthor = service.addNewAuthor(author);
      }
      service.addBookAuthor(addedAuthor, book);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
    }
    System.out.print("Want to add again [y/n]? ");
    String yesOrNo = getInputString();
    if (yesOrNo.equalsIgnoreCase("y")) {
      addBookAuthor(book);
    }
  }

  private void addBookCategory(Book book) {
    System.out.print("Input category: ");
    String name = getInputString();
    Category category = new Category(null, name);
    try {
      Category addedCategory = service.checkCategory(category);
      if (addedCategory == null || addedCategory.getId() == null) {
        addedCategory = service.addNewCategory(category);
      }
      service.addBookCategory(addedCategory, book);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
    }
    System.out.print("Want to add again [y/n]? ");
    String yesOrNo = getInputString();
    if (yesOrNo.equalsIgnoreCase("y")) {
      addBookCategory(book);
    }
  }

}
