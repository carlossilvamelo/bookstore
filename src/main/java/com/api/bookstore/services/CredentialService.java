package com.api.bookstore.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.bookstore.exceptions.ObjectNotFoundException;
import com.api.bookstore.models.Credential;
import com.api.bookstore.models.User;
import com.api.bookstore.repository.CredentialRepository;

@Service
public class CredentialService implements ICrudService<Credential, Long>{

	@Autowired
	private CredentialRepository credentialRepository;
	

	@Override
	public List<Credential> getAll(Pageable page) {
		// TODO Auto-generated method stub
		return credentialRepository.findAll();
	}

	@Override
	public Credential getById(Long id) {
		// TODO Auto-generated method stub
		return credentialRepository.findById(id)
				.orElseThrow(()-> new ObjectNotFoundException(String.format("There is no credential with  id=%d", id)));
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
