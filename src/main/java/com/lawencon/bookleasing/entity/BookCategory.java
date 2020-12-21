package com.lawencon.bookleasing.entity;

/**
 * @author Rian Rivaldo Rumapea
 */
public class BookCategory {

	private Long id;
	private Book book;
	private Category category;

	public BookCategory(Category category) {
		this.category = category;
	}

	public BookCategory(Category category, Book book) {
		this.category = category;
		this.book = book;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Book getBook() {
		return this.book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
