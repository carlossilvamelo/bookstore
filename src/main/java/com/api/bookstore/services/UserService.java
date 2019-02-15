package com.api.bookstore.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.api.bookstore.controllers.OrderResource;
import com.api.bookstore.dto.LoginDto;
import com.api.bookstore.dto.UserDto;
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
	public List<User> getAll() { 
		
		return userRepository.findAll(PageRequest.of(0, 2)).getContent();
	}


	@Override
	public User getById(Long id) {
		User user = userRepository.findById(id).get();
		if (user != null)
			return user;
		else
			throw new ObjectNotFoundException(String.format("There is no user with  id=%d", id));
	}

	@Override
	public User create(User entity) {
		return userRepository.save(entity);
	}

	@Override
	public void remove(User entity) {
		userRepository.delete(entity);
	}

	@Override
	public User update(User entity) {
		// TODO Auto-generated method stub
		return userRepository.save(entity);
	}



	/**
	 * 
	 * @param socialId
	 * @return
	 */
	public User getBySocialId(String socialId) {
		return userRepository.findOneBySocialId(socialId);
	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	public List<User> filterByName(String name) {
		return userRepository.findOneByNameContainingIgnoreCase(name);
	}

	/**
	 * 
	 * @param userId
	 * @return
	 */
	public User removeById(Long userId) {

		User user = this.getById(userId); 
		if(user == null)
			throw new ObjectNotFoundException("There is no user with id %d");
		
		BookOrder bookOrder = orderService.getByUserId(userId);// orders of user
		
		if(bookOrder != null)
			orderService.remove(bookOrder); // remove orders and the user
		else
			this.remove(user); // remove user without orders
		
		return user;
	}

	/**
	 * 
	 * 
	 * @param name
	 * @return
	 */
	public List<User> getByParam(String name) {
		List<User> userList = null;
		if (!name.isEmpty())
			userList = this.filterByName(name);
		else
			userList = this.getAll();
		return userList;
	}

	/**
	 * Create a user credential by user id
	 * 
	 * @param userId
	 * @param credential
	 * @return
	 */
	public User signup(Long userId, Credential credential) {
		User user = this.getById(userId);

		if (user != null && credential != null) {
			if (user.getCredential() != null) {
				user.getCredential().setUserName(credential.getUserName());
				user.getCredential().setPassword(credential.getPassword());
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

	/**
	 * 
	 * @param login
	 * @return
	 */
	public UserDto login(LoginDto login) {

		User user = userRepository.findUserByCredentials(login.getUserName(), login.getPassword());
		if (user == null)
			throw new ObjectNotFoundException("The credential is not valid");
		
		UserDto userDto = new UserDto(user);
		userDto.setToken(JwtManager.createJWT(user.getCredential().getUserName()
				,"issue", JwtManager.TOKEN_SUBJECT,JwtManager.TIMEOUT));
		
		return userDto;

	}
	

	

}
