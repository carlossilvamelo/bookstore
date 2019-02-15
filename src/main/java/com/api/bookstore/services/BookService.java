package com.api.bookstore.services;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.bookstore.models.Book;
import com.api.bookstore.repository.BookRepository;

@Service
public class BookService implements ICrudService<Book, Long> {

	@Autowired
	private BookRepository bookRepository;

	@Override
	public List<Book> getAll() {

		return bookRepository.findAll();
	}

	@Override
	public Book getById(Long id) {
		return bookRepository.findById(id).get();
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

	public List<Book> getAllByFilters(String title, String author, String orderBy) {		
		return bookRepository.findWithFilter(title, author, orderBy);
	}

}
