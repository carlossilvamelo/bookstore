package com.api.bookstore.models;


import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="BOOK_ORDER")
public class BookOrder{
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="order_id")
    private Long id;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	private User user;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar  orderDate;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dueDate;
	
	@OneToMany(mappedBy = "bookOrder", cascade = CascadeType.PERSIST)
	private List<Book> book;
	
	
	public BookOrder(User user, Calendar orderDate, Calendar dueDate, List<Book> book) {
		super();
		this.user = user;
		this.orderDate = orderDate;
		this.dueDate = dueDate;
		this.book = book;
	}

	public BookOrder() {}
	
	
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



	public Calendar getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Calendar orderDate) {
		this.orderDate = orderDate;
	}

	public Calendar getDueDate() {
		return dueDate;
	}

	public void setDueDate(Calendar dueDate) {
		this.dueDate = dueDate;
	}
	
	

}
