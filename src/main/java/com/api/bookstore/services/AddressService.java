package com.api.bookstore.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.bookstore.exceptions.ObjectNotFoundException;
import com.api.bookstore.models.Address;
import com.api.bookstore.repository.AddressRepository;


@Service
public class AddressService implements ICrudService<Address, Long>{

	@Autowired
	private AddressRepository addressRepository;
	
	@Override
	public List<Address> getAll(Pageable page) {

		return addressRepository.findAll(page).getContent();
	}
	
	public List<Address> getAllWithFilters(String pageParam, String sizeParam) {
		Pageable page = PageRequest.of(Integer.parseInt(pageParam), Integer.parseInt(sizeParam));
		
		return addressRepository.findAll(page).getContent();
	}

	@Override
	public Address getById(Long id) {

		return addressRepository.findById(id)
				.orElseThrow(
				()->new ObjectNotFoundException(String.format("Addres with id=%d not exists", id)));
	}

	@Override
	public Address create(Address entity) {
		// TODO Auto-generated method stub
		return addressRepository.save(entity);
	}

	@Override
	public void remove(Address entity) {
		// TODO Auto-generated method stub
		addressRepository.delete(entity);
	}

	@Override
	public Address update(Address entity) {
		// TODO Auto-generated method stub
		return addressRepository.save(entity);
	}

}
