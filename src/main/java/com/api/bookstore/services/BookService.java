package com.api.bookstore.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.api.bookstore.exceptions.ObjectNotFoundException;
import com.api.bookstore.models.Book;
import com.api.bookstore.repository.BookRepository;

@Service
public class BookService implements ICrudService<Book, Long> {

	@Autowired
	private BookRepository bookRepository;

	@Override
	public List<Book> getAll(Pageable page) {
		//return bookRepository.findAll();
		return null;
	}

	public List<Book> getAllWithFilters(String title, String author, String orderBy, String pageParam, String sizeParam) {

		Sort sort = Arrays.asList("title", "author", "category").contains(orderBy) ? Sort.by(orderBy)
				: Sort.by("title");
		int page = Integer.parseInt(pageParam);
		int size = Integer.parseInt(sizeParam);

		PageRequest pageRequest = PageRequest.of(page, size, sort);
		return bookRepository.findWithFilter(title, author, pageRequest);
	}

	@Override
	public Book getById(Long id) {
		return bookRepository.findById(id)
				.orElseThrow(()->new ObjectNotFoundException(String.format("There is no book with  id=%d", id)) );
	}

	@Override
	public Book create(Book entity) {
		return bookRepository.save(entity);
	}

	@Override
	public void remove(Book entity) {
		bookRepository.delete(entity);

	}

	@Override
	public Book update(Book entity) {
		return bookRepository.save(entity);
	}
	

	public List<Book> updateAll(List<Book> entities) {
	
		entities.forEach((b)-> {
			b.setFree(true);
			b.setOrder(null);
		});
		return bookRepository.saveAll(entities);
	}

}
