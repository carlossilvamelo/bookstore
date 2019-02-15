package com.api.bookstore.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.api.bookstore.models.BookOrder;
import com.api.bookstore.services.OrderService;

@RestController
@RequestMapping("/order")
public class OrderResource {

	@Autowired
	private OrderService orderService;

	@GetMapping
	public List<BookOrder> getAll() {
		return orderService.getAll();
	}

	@GetMapping("{id}")
	public BookOrder getById(@PathVariable Long id) {
		return orderService.getById(id);
	}

	@GetMapping("/user/{userId}/book/{bookId}")
	public BookOrder orderBook(@PathVariable Long userId, @PathVariable Long bookId) {
		return orderService.orderBook(userId, bookId);
	}

	@GetMapping("/{orderId}/close")
	public BookOrder devolution(@PathVariable Long orderId) {
		return orderService.devolution(orderId);
	}

}
