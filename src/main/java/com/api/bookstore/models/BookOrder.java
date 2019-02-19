package com.api.bookstore.models;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "BOOK_ORDER")
public class BookOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private Long id;

	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "user_fk")
	private User user;

	@Column(name = "order_date")
	private LocalDate orderDate;
	@Column(name = "due_date")
	private LocalDate dueDate;

	@OneToMany(mappedBy = "bookOrder", cascade = CascadeType.REFRESH)
	@JsonIgnore
	private List<Book> book;

	public BookOrder(User user, LocalDate orderDate, LocalDate dueDate, List<Book> book) {
		super();
		this.user = user;
		this.orderDate = orderDate;
		this.dueDate = dueDate;
		this.book = book;
	}

	public BookOrder() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Book> getBook() {
		return book;
	}

	public void setBook(List<Book> book) {
		this.book = book;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

}
