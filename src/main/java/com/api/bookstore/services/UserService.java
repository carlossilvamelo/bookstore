package com.api.bookstore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.api.bookstore.dto.UserDto;
import com.api.bookstore.exceptions.ObjectNotFoundException;
import com.api.bookstore.exceptions.SignupRequestException;
import com.api.bookstore.models.BookOrder;
import com.api.bookstore.models.User;
import com.api.bookstore.repository.BookOrderRepository;
import com.api.bookstore.repository.UserRepository;

@Service
public class UserService implements ICrudService<User, Long> {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BookOrderRepository orderRepository;

	@Override
	public Page<User> getAll(String pageNumber, String pageSize) {
		Pageable page = PageRequest.of(Integer.parseInt(pageNumber), Integer.parseInt(pageSize));
		return userRepository.findAll(page);
	}

	@Override
	public User getById(Long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException(String.format("There is no user with  id=%d", id)));
	}

	@Override
	public User create(User entity) {
		return userRepository.save(entity);
	}

	@Override
	public User remove(User entity) {
		userRepository.delete(entity);
		return entity;
	}

	@Override
	public User update(User entity) {
		return userRepository.save(entity);
	}

	public User getBySocialId(String socialId) {
		return userRepository.findOneBySocialId(socialId);
	}

	public User removeById(Long userId) {

		User user = this.getById(userId);
		BookOrder bookOrder = orderRepository.findOrderByUserId(userId);

		if (bookOrder != null)
			orderRepository.delete(bookOrder);
		else
			this.remove(user);

		return user;
	}

	public Page<User> getAllWithFilter(String name, String pageNumber, String pageSize) {
		Pageable pageRequest = PageRequest.of(Integer.parseInt(pageNumber), Integer.parseInt(pageSize));
		return userRepository.findOneByNameContainingIgnoreCase(name, pageRequest);
	}

	public User signup(UserDto userDto) {
		if (userDto.getPassword() == null || userDto.getUserName() == null || userDto.getPassword().isEmpty()
				|| userDto.getUserName().isEmpty())
			throw new SignupRequestException("Invalid Password or User Name. Can't set empty or null values");
		return userRepository.save(userDto.toUser());
	}

}
