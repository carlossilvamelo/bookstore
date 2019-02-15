package com.api.bookstore.services;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.api.bookstore.exceptions.ObjectNotFoundException;
import com.api.bookstore.models.Book;
import com.api.bookstore.models.BookOrder;
import com.api.bookstore.models.User;
import com.api.bookstore.repository.BookOrderRepository;

@Service
public class OrderService implements ICrudService<BookOrder, Long> {

	@Autowired
	private BookOrderRepository orderRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private BookService bookService;

	@Override
	public List<BookOrder> getAll() {
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
	public BookOrder orderBook(Long userId, Long bookId) {
		Calendar currentDate = new GregorianCalendar();
		Calendar dueDate = new GregorianCalendar(currentDate.getTime().getYear(), currentDate.getTime().getMonth() + 1,
				currentDate.getTime().getDay());

		User user = userService.getById(userId);// verify the user
		if (user == null)
			throw new ObjectNotFoundException(String.format("There is no user with id = %d", userId));

		Book book = bookService.getById(bookId);// verify the book
		if (book == null)
			throw new ObjectNotFoundException(String.format("There is no book with id = %d", bookId));

		BookOrder order = new BookOrder(user, currentDate, dueDate, null);// new order

		if (book.isFree()) {
			order = this.orderBookToUser(user, book, order);
			this.create(order);

		} else
			throw new ObjectNotFoundException(String.format("The book with id %d is not free", book.getId()));

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

		this.remove(order);
		return order;
	}

	/**
	 * create a new order for a user to a book
	 * 
	 * @param user
	 * @param book
	 * @param order
	 * @return
	 */
	public BookOrder orderBookToUser(User user, Book book, BookOrder order) {

		order.setUser(user);
		order.setBook(Arrays.asList(book));

		return order;
	}

}
