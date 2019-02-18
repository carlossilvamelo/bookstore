package com.api.bookstore.controllers;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
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

	@GetMapping("/{userId}")
	public BookOrder orderBook(@PathVariable Long userId) {

		return orderService.getByUserId(userId);
	}

	@PutMapping("/{userId}/open")
	public BookOrder orderBook(@PathVariable Long userId, @Valid @RequestBody List<Long> bookIds) {
		return orderService.createNewOrderToUser(userId, bookIds);
	}

	@GetMapping("/{orderId}/close")
	public BookOrder devolution(@PathVariable Long orderId) {
		return orderService.devolution(orderId);
	}
}
