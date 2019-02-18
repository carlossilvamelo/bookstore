package com.api.bookstore.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.api.bookstore.exceptions.ObjectNotFoundException;
import com.api.bookstore.models.Book;
import com.api.bookstore.repository.BookRepository;

@Service
public class BookService implements ICrudService<Book, Long> {

	private static final String[] FILTER_TYPES = new String[] { "title", "author", "category" };
	private static final String DEFAULT_FILTER = "title";

	@Autowired
	private BookRepository bookRepository;

	@Override
	public Page<Book> getAll(String pageNumber, String pageSize) {
		Pageable page = PageRequest.of(Integer.parseInt(pageNumber), Integer.parseInt(pageSize));
		return bookRepository.findAll(page);
	}

	public List<Book> getAllWithFilters(String title, String author, String orderBy, String pageParam,
			String sizeParam) {

		Sort sort = Arrays.asList(FILTER_TYPES).contains(orderBy) ? Sort.by(orderBy) : Sort.by(DEFAULT_FILTER);
		int pageNumber = Integer.parseInt(pageParam);
		int pageSize = Integer.parseInt(sizeParam);
		PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);

		return bookRepository.findWithFilter(title, author, pageRequest);
	}

	@Override
	public Book getById(Long id) {
		return bookRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException(String.format("There is no book with  id=%d", id)));
	}

	@Override
	public Book create(Book entity) {
		return bookRepository.save(entity);
	}

	@Override
	public Book remove(Book entity) {
		bookRepository.delete(entity);
		return entity;
	}

	@Override
	public Book update(Book entity) {
		return bookRepository.save(entity);
	}

	public List<Book> updateAll(List<Book> entities) {

		entities.forEach((b) -> {
			b.setFree(true);
			b.setOrder(null);
		});

		return bookRepository.saveAll(entities);
	}

}
