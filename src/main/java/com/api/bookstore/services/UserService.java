package com.api.bookstore.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.api.bookstore.exceptions.ObjectNotFoundException;
import com.api.bookstore.models.BookOrder;
import com.api.bookstore.models.Credential;
import com.api.bookstore.models.User;
import com.api.bookstore.repository.UserRepository;

@Service
public class UserService implements ICrudService<User, Long> {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderService orderService;

	@Override
	public Page<User> getAll(String pageNumber, String pageSize) {
		Pageable page = PageRequest.of(Integer.parseInt(pageNumber), Integer.parseInt(pageSize));
		return userRepository.findAll(page);
	}

	@Override
	public User getById(Long id) {

		return userRepository.findById(id)
				.orElseThrow(()->new ObjectNotFoundException(String.format("There is no user with  id=%d", id)));
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

	public List<User> filterByName(String name) {
		 return userRepository.findOneByNameContainingIgnoreCase(name);
	}

	public User removeById(Long userId) {

		User user = this.getById(userId);
		if (user == null)
			throw new ObjectNotFoundException("There is no user with id %d");

		BookOrder bookOrder = orderService.getByUserId(userId);

		if (bookOrder != null)
			orderService.remove(bookOrder);
		else
			this.remove(user);

		return user;
	}

	/**
	 * REVIEWW
	 * @param name
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public List<User> getByParam(String name, String pageNumber, String pageSize) {
		Pageable page = PageRequest.of(Integer.parseInt(pageNumber), Integer.parseInt(pageSize));
		
		List<User> userList = null;
		if (!name.isEmpty())
			userList = this.filterByName(name);
		else
			userList = this.getAll(pageNumber, pageSize).getContent();
		return userList;
	}

	/**
	 * REVIEW
	 * @param userId
	 * @param credential
	 * @return
	 */
	public User signup(Long userId, Credential credential) {
		User user = this.getById(userId);

		if (user != null && credential != null) {
			if (user.getCredential() != null) {
				user.getCredential().setUserName(credential.getUserName());
				user.getCredential()
				.setPassword(new BCryptPasswordEncoder().encode(credential.getPassword()));
				user = this.update(user);
				return user;
			} else {
				user.setCredential(credential);
				user = this.create(user);
				return user;
			}
		}
		return user;
	}

}
