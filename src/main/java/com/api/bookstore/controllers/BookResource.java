package com.api.bookstore.controllers;

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
	public ResponseEntity<List<Book>> getAll(@RequestParam(defaultValue = "orderBy") String orderBy,
			@RequestParam(defaultValue = "title") String title, @RequestParam(defaultValue = "author") String author,
			@RequestParam(defaultValue = "category") String category) {

		List<Book> bookList = null;

		if (!title.equals("title") || !author.equals("author")) {

			if (!title.equals("title"))
				bookList = bookService.findInTitle(title);

			if (!author.equals("author"))
				bookList = bookService.findInAuthors(author);

//			if (!category.equals("category"))
//				bookLIst = bookService.findInCategories(category);

		} else
			bookList = bookService.getAll();

		if (bookList.size() > 2) {
			switch (orderBy) {
			case "title":
				bookService.sortByTitle(bookList);
				break;
			case "author":
				bookService.sortByAuthor(bookList);
				break;
			case "category":
				// bookService.sortByCategory(bookList);
				break;
			default:
				break;

			}
		}

		return ResponseEntity.ok().body(bookList);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Book> getById(@PathVariable Long id) {
		Book book = bookService.getById(id);

		return book == null ? ResponseEntity.notFound().build() : ResponseEntity.ok().body(book);
	}

	@PostMapping("")
	public ResponseEntity<Book> create(@Valid @RequestBody Book book) {
		bookService.create(book);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("")
	public ResponseEntity<Book> update(@Valid @RequestBody Book book) {

		Book response = bookService.update(book);
		return response == null ? ResponseEntity.notFound().build() : ResponseEntity.ok().body(response);
			
	}

}
