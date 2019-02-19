package com.api.bookstore.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.api.bookstore.exceptions.ObjectNotFoundException;
import com.api.bookstore.models.Book;
import com.api.bookstore.models.BookOrder;
import com.api.bookstore.models.User;
import com.api.bookstore.repository.BookOrderRepository;
import com.api.bookstore.repository.BookRepository;
import com.api.bookstore.repository.UserRepository;

@Service
public class OrderService implements ICrudService<BookOrder, Long> {

	@Autowired
	private BookOrderRepository orderRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BookRepository bookRepository;

	@Override
	public Page<BookOrder> getAll(String pageNumber, String pageSize) {
		Pageable page = PageRequest.of(Integer.parseInt(pageNumber), Integer.parseInt(pageSize));
		return orderRepository.findAll(page);
	}

	@Override
	public BookOrder getById(Long id) {
		return orderRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException(String.format("There is no order with id = %d", id)));
	}

	@Override
	public BookOrder create(BookOrder entity) {
		return orderRepository.save(entity);
	}

	@Override
	public BookOrder remove(BookOrder entity) {
		orderRepository.delete(entity);
		return entity;
	}

	@Override
	public BookOrder update(BookOrder entity) {

		return orderRepository.save(entity);
	}

	public BookOrder getByUserId(Long userId) {
		return Optional.ofNullable(orderRepository.findOrderByUserId(userId)).orElseThrow(
				() -> new ObjectNotFoundException(String.format("There is no Order for user with id = %d", userId)));
	}

	public BookOrder createNewOrderToUser(Long userId, List<Long> bookIds) {

		LocalDate currentDate = LocalDate.now();
		LocalDate dueDate = currentDate.plusMonths(1);
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ObjectNotFoundException(String.format("There is no user with id = %d", userId)));

		BookOrder order = new BookOrder(user, currentDate, dueDate, null);
		order = orderRepository.save(order);
		for (Long bookId : bookIds) {

			Book book = bookRepository.findById(bookId).orElseThrow(
					() -> new ObjectNotFoundException(String.format("There is no book with id = %d", bookId)));

			if (book.isFree()) {
				book.setOrder(order);
				book.setFree(false);
				bookRepository.save(book);
			} else
				throw new ObjectNotFoundException(String.format("The book with id %d is not free", book.getId()));

		}

		return order;
	}

	public BookOrder closeOrder(Long orderId) {

		BookOrder order = this.getById(orderId);
		List<Book> bookList = order.getBook();
		bookList.forEach(b -> b.setOrder(null));
		bookRepository.saveAll(bookList);
		this.remove(order);

		return order;
	}
}
