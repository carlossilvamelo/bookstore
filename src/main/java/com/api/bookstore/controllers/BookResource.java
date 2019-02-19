package com.api.bookstore.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.api.bookstore.models.Book;
import com.api.bookstore.services.BookService;

@RestController
@RequestMapping("/books")
public class BookResource {

	@Autowired
	private BookService bookService;

	@GetMapping
	public List<Book> getAll(@RequestParam(defaultValue = "") String orderBy,
			@RequestParam(defaultValue = "") String title, @RequestParam(defaultValue = "") String author,
			@RequestParam(defaultValue = "") Long category, @RequestParam(defaultValue = "0") String pageNumber,
			@RequestParam(defaultValue = "10") String pageSize) {

		return bookService.getAllWithFilters(title, author, orderBy, pageNumber, pageSize);
	}

	@GetMapping("/{bookId}")
	public Book getById(@PathVariable Long bookId) {
		return bookService.getById(bookId);
	}

	@PostMapping
	public Book create(@RequestBody Book book) {
		return bookService.create(book);
	}

	@PutMapping
	public Book update(@RequestBody Book book) {
		return bookService.update(book);
	}

	@DeleteMapping("/{bookId}")
	public Book delete(@PathVariable Long bookId) {
		return bookService.removeById(bookId);
	}

}
