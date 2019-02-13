package com.api.bookstore.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.bookstore.models.Credential;
import com.api.bookstore.models.User;
import com.api.bookstore.repository.UserRepository;

@Service
public class UserService implements ICrudService<User, Long> {

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> getAll() {
		return userRepository.findAll();
	}

	@Override
	public User getById(Long id) {
		// TODO Auto-generated method stub
		return userRepository.findOne(id);
	}

	@Override
	public User create(User entity) {
		// TODO Auto-generated method stub
		return userRepository.save(entity);
	}

	@Override
	public void remove(User entity) {
		// TODO Auto-generated method stub
		userRepository.delete(entity);
	}

	@Override
	public User update(User entity) {
		// TODO Auto-generated method stub
		return userRepository.save(entity);
	}

	public User getByCredentialId(Long ind) {
		Credential c = new Credential();
		c.setId(new Long(1));
		return userRepository.findOneByCredential(c);
	}
	
	public User getBySocialId(String socialId) {
		return userRepository.findOneBySocialId(socialId);
	}
	
	public List<User> filterByName(String name) {
		return userRepository.findOneByNameContainingIgnoreCase(name);
	}

}
