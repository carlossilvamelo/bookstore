package com.api.bookstore.services;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.api.bookstore.exceptions.ObjectNotFoundException;
import com.api.bookstore.models.Book;
import com.api.bookstore.models.BookOrder;
import com.api.bookstore.models.User;
import com.api.bookstore.repository.BookOrderRepository;
import com.api.bookstore.repository.BookRepository;

@Service
public class OrderService implements ICrudService<BookOrder, Long> {

	@Autowired
	private BookOrderRepository orderRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private BookService bookService;

	@Override
	public List<BookOrder> getAll(Pageable page) {
		return orderRepository.findAll();
	}

	@Override
	public BookOrder getById(Long id) {
		return orderRepository.findById(id).get();
	}

	@Override
	public BookOrder create(BookOrder entity) {
		return orderRepository.save(entity);
	}

	@Override
	public void remove(BookOrder entity) {
		orderRepository.delete(entity);
	}

	@Override
	public BookOrder update(BookOrder entity) {
		return orderRepository.save(entity);
	}

	/**
	 * 
	 * @param userId
	 * @return
	 */
	public BookOrder getByUserId(Long userId) {
		//User user = userService.getById(userId);

		return orderRepository.findOrderByUserId(userId);
	}


	
	/**
	 * 
	 * @param userId
	 * @param bookId
	 * @return
	 */
	public BookOrder orderBook(Long userId, List<Long> bookIds) {
		
		LocalDate currentDate = LocalDate.now();
		LocalDate dueDate = LocalDate.of(currentDate.getYear(), currentDate.getMonthValue()+1
				, currentDate.getDayOfMonth());
		User user = userService.getById(userId);// verify the user
		if (user == null)
			throw new ObjectNotFoundException(String.format("There is no user with id = %d", userId));

		BookOrder order = new BookOrder(user, currentDate, dueDate, null);// new order
		order = orderRepository.save(order);
		
		for(Long bookId : bookIds){
			
			Book book = bookService.getById(bookId);// verify the book
			if (book == null)
				throw new ObjectNotFoundException(String.format("There is no book with id = %d", bookId));	


			if (book.isFree()) {
				book.setOrder(order);
				book.setFree(false);
				bookService.create(book);
			} else
				throw new ObjectNotFoundException(String.format("The book with id %d is not free", book.getId()));
			
		};

		return order;
	}

	/**
	 * 
	 * @param orderId
	 * @return
	 */
	public BookOrder devolution(Long orderId) {
		BookOrder order = this.getById(orderId);

		
		if (order == null)
			throw new ObjectNotFoundException(String.format("Order with id %d not exists", orderId));
		
		List<Book> bookList = order.getBook();
		bookService.updateAll(bookList);
		this.remove(order);
		return order;
	}



}
