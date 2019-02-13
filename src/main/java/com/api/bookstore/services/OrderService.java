package com.api.bookstore.services;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.bookstore.models.Book;
import com.api.bookstore.models.BookOrder;
import com.api.bookstore.models.User;
import com.api.bookstore.repository.BookOrderRepository;

@Service
public class OrderService implements ICrudService<BookOrder, Long>{

	@Autowired
	private BookOrderRepository orderRepository;
	
	@Override
	public List<BookOrder> getAll() {
		// TODO Auto-generated method stub
		return orderRepository.findAll();
	}

	@Override
	public BookOrder getById(Long id) {
		// TODO Auto-generated method stub
		return orderRepository.findOne(id);
	}

	@Override
	public BookOrder create(BookOrder entity) {
		// TODO Auto-generated method stub
		
		return orderRepository.save(entity);
	}

	@Override
	public void remove(BookOrder entity) {
		// TODO Auto-generated method stub
		orderRepository.delete(entity);
	}

	@Override
	public BookOrder update(BookOrder entity) {
		// TODO Auto-generated method stub
		return orderRepository.save(entity);
	}
	
	
	public BookOrder orderBookToUser(User user, Book book, BookOrder order) {
		List<Book> newBookList = null;
		if(order != null && user != null && book!= null) {
			if(book.getOrder() == null) { // is not ordered yet
				System.out.println("book.getOrder() == null");
				order.setUser(user);
				
				if(order.getBook() != null) {
					newBookList = Stream.concat(order.getBook().stream(), Arrays.asList(book).stream())
							.collect(Collectors.toList());
					order.setBook(newBookList);
				}
				
			}

		}
		
		return order;
	}

	
	
}
