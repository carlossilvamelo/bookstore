package com.api.bookstore.dto;

import java.io.Serializable;

import com.api.bookstore.enums.Category;
import com.api.bookstore.models.Book;

public class BookDto implements Serializable {

	private static final long serialVersionUID = 3364130656211428667L;

	private String title;
	private String resume;
	private String author;
	private boolean free;

	public boolean isFree() {
		return free;
	}

	public void setFree(boolean free) {
		this.free = free;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	private Category category;

	public BookDto() {
	}

	public BookDto(Book book) {
		this.title = book.getTitle();
		this.resume = book.getResume();
		this.author = book.getAuthor();
		this.category = book.getCategory();
	}

	public static Book fromDto(BookDto bookDto) {
		return new Book(bookDto.getTitle(), bookDto.getResume(), bookDto.getAuthor(), bookDto.isFree(), null,
				bookDto.getCategory());
	}
}
