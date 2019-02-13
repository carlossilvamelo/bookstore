package com.api.bookstore.dto;

import java.io.Serializable;

import com.api.bookstore.enums.Category;
import com.api.bookstore.models.Book;
import com.api.bookstore.models.BookOrder;


public class BookDto  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String title;
	private String resume;
	private String author;
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
	
	public BookDto() {}
	
	public BookDto(Book book) {
		this.title = book.getTitle();
		this.resume = book.getResume();
		this.author = book.getAuthor();
		this.category = book.getCategory();
	}
	
	public static Book fromDto(BookDto bookDto) {
		return new Book(bookDto.getTitle(), bookDto.getResume(), bookDto.getAuthor(),null, bookDto.getCategory());
	}
}
