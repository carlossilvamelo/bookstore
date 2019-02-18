package com.api.bookstore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.bookstore.exceptions.ObjectNotFoundException;
import com.api.bookstore.models.Credential;
import com.api.bookstore.repository.CredentialRepository;

@Service
public class CredentialService implements ICrudService<Credential, Long> {

	@Autowired
	private CredentialRepository credentialRepository;

	@Override
	public Page<Credential> getAll(String pageNumber, String pageSize) {
		Pageable page = PageRequest.of(Integer.parseInt(pageNumber), Integer.parseInt(pageSize));
		return credentialRepository.findAll(page);
	}

	@Override
	public Credential getById(Long id) {
		return credentialRepository.findById(id).orElseThrow(
				() -> new ObjectNotFoundException(String.format("There is no credential with  id=%d", id)));
	}

	@Override
	public Credential create(Credential entity) {
		return credentialRepository.save(entity);
	}

	@Override
	public Credential remove(Credential entity) {
		credentialRepository.delete(entity);
		return entity;
	}

	@Override
	public Credential update(Credential entity) {
		return credentialRepository.save(entity);
	}
}
