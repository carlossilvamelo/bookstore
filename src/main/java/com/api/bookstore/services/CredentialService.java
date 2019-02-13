package com.api.bookstore.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.bookstore.models.Credential;
import com.api.bookstore.models.User;
import com.api.bookstore.repository.CredentialRepository;

@Service
public class CredentialService implements ICrudService<Credential, Long>{

	@Autowired
	private CredentialRepository credentialRepository;

	@Autowired
	private UserService userService;
	
	public User verifyUser(String userName, String password) {
		Credential credential = credentialRepository.findByUserName(userName);
		
		User user = null;
		if (credential != null) {
			//user = credential.getUser();
			if (credential.getPassword().equals(password)) {
				
				user = userService.getByCredentialId(credential.getId());
			}
				
		}
		
		return user;
	}

	@Override
	public List<Credential> getAll() {
		// TODO Auto-generated method stub
		return credentialRepository.findAll();
	}

	@Override
	public Credential getById(Long id) {
		// TODO Auto-generated method stub
		return credentialRepository.findOne(id);
	}

	@Override
	public Credential create(Credential entity) {
		// TODO Auto-generated method stub
		return credentialRepository.save(entity);
	}

	@Override
	public void remove(Credential entity) {
		// TODO Auto-generated method stub
		credentialRepository.delete(entity);
	}

	@Override
	public Credential update(Credential entity) {
		// TODO Auto-generated method stub
		return credentialRepository.save(entity);
	}
}
