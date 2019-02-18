package com.api.bookstore.controllers;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.bookstore.dto.BookDto;
import com.api.bookstore.models.Book;
import com.api.bookstore.services.BookService;

@RestController
@RequestMapping("/book")
public class BookResource {

	@Autowired
	private BookService bookService;

	@GetMapping("")
	public List<Book> getAll(@RequestParam(defaultValue = "") String orderBy,
			@RequestParam(defaultValue = "") String title, @RequestParam(defaultValue = "") String author,
			@RequestParam(defaultValue = "") Long category, @RequestParam(defaultValue = "0") String page,
			@RequestParam(defaultValue = "10") String size) {

		return bookService.getAllWithFilters(title, author, orderBy, page, size);
	}

	@GetMapping("/{id}")
	public Book getById(@PathVariable Long id) {
		return bookService.getById(id);
	}

	@PostMapping("")
	public Book create(@Valid @RequestBody Book book) {
		return bookService.create(book);
	}

	@PutMapping("")
	public Book update(@Valid @RequestBody Book book) {
		return bookService.update(book);
	}

}
