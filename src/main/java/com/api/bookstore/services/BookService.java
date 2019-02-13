package com.api.bookstore.services;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.bookstore.models.Book;
import com.api.bookstore.repository.BookRepository;

@Service
public class BookService implements ICrudService<Book, Long>{

	@Autowired
	private BookRepository bookRepository;
	
	@Override
	public List<Book> getAll() {
		
		return bookRepository.findAll();
	}

	@Override
	public Book getById(Long id) {
		// TODO Auto-generated method stub
		return bookRepository.findOne(id);
	}

	@Override
	public Book create(Book entity) {
		// TODO Auto-generated method stub
		return bookRepository.save(entity);
	}

	@Override
	public void remove(Book entity) {
		bookRepository.delete(entity);
		
	}

	@Override
	public Book update(Book entity) {
		// TODO Auto-generated method stub
		return bookRepository.save(entity);
	}
	
	public List<Book> findInTitle(String title){
		return bookRepository.findByTitleContainingIgnoreCase(title);
	}
	
	public List<Book> findInAuthors(String author){
		return bookRepository.findByAuthorContainingIgnoreCase(author);
	}
	
	public List<Book> findInCategories(Integer category){
		return bookRepository.findByCategory(category);
	}
	
	
	public List<Book> sortByTitle(List<Book> bookList){
		Collections.sort(bookList, (a, b) -> a.getTitle().toLowerCase().compareTo(b.getTitle().toLowerCase()));
		return bookList;
	}
	
	public List<Book> sortByAuthor(List<Book> bookList){
		Collections.sort(bookList,
				(a, b) -> a.getAuthor().toLowerCase().compareTo(b.getAuthor().toLowerCase()));
		return bookList;
	}
	
	public List<Book> sortByCategory(List<Book> bookList){
		Collections.sort(bookList,
				(a, b) -> a.getAuthor().toLowerCase().compareTo(b.getAuthor().toLowerCase()));
		return bookList;
	}

	
}
