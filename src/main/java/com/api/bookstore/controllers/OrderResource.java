package com.api.bookstore.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.bookstore.models.BookOrder;
import com.api.bookstore.models.User;
import com.api.bookstore.services.OrderService;

@RestController
@RequestMapping("/order")
public class OrderResource {

	@Autowired
	private OrderService orderService;

	@GetMapping
	public List<BookOrder> getAll(Pageable page) {
		return orderService.getAll(page);
	}

	@GetMapping("{id}")
	public BookOrder getById(@PathVariable Long id) {
		return orderService.getById(id);
	}

	@PutMapping("/{userId}")
	public BookOrder orderBook(@PathVariable Long userId, @Valid @RequestBody List<Long> bookIds) {
		return orderService.orderBook(userId, bookIds);
	}

	@GetMapping("/{orderId}/close")
	public BookOrder devolution(@PathVariable Long orderId) {
		return orderService.devolution(orderId);
	}
	

}
