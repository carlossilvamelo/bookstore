package com.api.bookstore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.api.bookstore.exceptions.ObjectNotFoundException;
import com.api.bookstore.models.Address;
import com.api.bookstore.repository.AddressRepository;

@Service
public class AddressService implements ICrudService<Address, Long> {

	@Autowired
	private AddressRepository addressRepository;

	@Override
	public Page<Address> getAll(String pageNumber, String pageSize) {
		Pageable pageRequest = PageRequest.of(Integer.parseInt(pageNumber), Integer.parseInt(pageSize));
		return addressRepository.findAll(pageRequest);
	}

	public Page<Address> getAllWithFilters(String pageParam, String sizeParam) {
		Pageable pageRequest = PageRequest.of(Integer.parseInt(pageParam), Integer.parseInt(sizeParam));
		return addressRepository.findAll(pageRequest);
	}

	@Override
	public Address getById(Long id) {
		return addressRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException(String.format("Addres with id=%d not exists", id)));
	}

	@Override
	public Address create(Address entity) {
		return addressRepository.save(entity);
	}

	@Override
	public Address remove(Address entity) {
		addressRepository.delete(entity);
		return entity;
	}

	@Override
	public Address update(Address entity) {
		return addressRepository.save(entity);
	}

}
