package com.api.bookstore.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;

import com.api.bookstore.enums.Category;

@Entity
public class Book{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="book_id")
	private Long id;
	private String title;
	private String resume;
	private String author;
	private boolean free;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="order_id")
	private BookOrder bookOrder;
	


	@Enumerated(EnumType.ORDINAL)
	private Category category;
	
	
	public Book() {};

	public Book(String title, String resume, String author,boolean free, BookOrder order, Category category) {
		super();
		this.title = title;
		this.resume = resume;
		this.author = author;
		this.bookOrder = order;
		this.category = category;
		this.free = free;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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

	public BookOrder getOrder() {
		return bookOrder;
	}

	public void setOrder(BookOrder bookOrder) {
		this.bookOrder = bookOrder;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public boolean isFree() {
		return free;
	}

	public void setFree(boolean free) {
		this.free = free;
	}

	

}
