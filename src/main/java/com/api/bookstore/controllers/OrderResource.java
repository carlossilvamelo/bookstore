package com.api.bookstore.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.api.bookstore.models.BookOrder;
import com.api.bookstore.services.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderResource {

	@Autowired
	private OrderService orderService;

	@GetMapping
	public List<BookOrder> getAll(@RequestParam(defaultValue = "0") String pageNumber,
			@RequestParam(defaultValue = "10") String pageSize) {

		return orderService.getAll(pageNumber, pageSize).getContent();
	}

	@GetMapping("/{id}")
	public BookOrder getById(@PathVariable Long id) {
		return orderService.getById(id);
	}

	@GetMapping("/user/{userId}")
	public BookOrder getOrderByUserId(@PathVariable Long userId) {

		return orderService.getByUserId(userId);
	}

	@PostMapping("/{userId}")
	public BookOrder orderBook(@PathVariable Long userId, @RequestBody List<Long> bookIds) {
		return orderService.createNewOrderToUser(userId, bookIds);
	}

	@DeleteMapping("/{orderId}")
	public BookOrder closeOrder(@PathVariable Long orderId) {
		return orderService.closeOrder(orderId);
	}
}
