package com.api.bookstore.controllers;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.criteria.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.bookstore.models.Book;
import com.api.bookstore.models.BookOrder;
import com.api.bookstore.models.User;
import com.api.bookstore.services.BookService;
import com.api.bookstore.services.OrderService;
import com.api.bookstore.services.UserService;

@RestController
@RequestMapping("/order")
public class OrderResource {
	private final String AUTH_HEADER = "Authorization";
	@Autowired
	private OrderService orderService;
	@Autowired
	private UserService userService;
	
	@Autowired
	private BookService bookService;
	
	@GetMapping
	public ResponseEntity<List<BookOrder>> getAll(){
		List<BookOrder> orderList = orderService.getAll();
		return ResponseEntity.ok(orderList);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<BookOrder> getById(@PathVariable Long id){
		BookOrder order = orderService.getById(id);
		return order != null ? ResponseEntity.ok(order) : ResponseEntity.notFound().build();
	}
	
	@GetMapping("/user/{userId}/book/{bookId}")
	public ResponseEntity<BookOrder> orderBook(@PathVariable Long userId,
			@PathVariable Long bookId){
		
		// creating new Order
		Calendar currentDate = new GregorianCalendar();
		Calendar dueDate = new GregorianCalendar(currentDate.getTime().getYear(), 
				currentDate.getTime().getMonth()+1, currentDate.getTime().getDay());
		User user = userService.getById(userId);
		BookOrder order = new BookOrder(user, currentDate, dueDate, null);
		Book book = bookService.getById(bookId);
		
		if(book.isFree()) {
			order = orderService.orderBookToUser(user, book, order);
			orderService.create(order);
			return ResponseEntity.ok().body(order);
		}else
			return ResponseEntity.notFound().build();
		
		
	}
	
	@GetMapping("/{orderId}/close")
	public ResponseEntity<BookOrder> devolution(@PathVariable Long orderId){
		BookOrder order = orderService.getById(orderId);
		
		orderService.remove(order);
		return ResponseEntity.ok().build();
	}
	
	
	
	
}
