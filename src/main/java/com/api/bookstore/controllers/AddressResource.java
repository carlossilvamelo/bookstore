package com.api.bookstore.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.bookstore.models.Address;
import com.api.bookstore.services.AddressService;

@RestController
@RequestMapping("address")
public class AddressResource {
	
	@Autowired
	private AddressService addressService;
	
	@GetMapping("")
	public List<Address> getAll(@RequestParam(defaultValue="0") String pageParam
			, @RequestParam(defaultValue="10") String sizeParam){
		
		return addressService.getAllWithFilters(pageParam, sizeParam);
	}
	
	@GetMapping("/{addressId}")
	public Address getById(@PathVariable Long addressId) {
		return addressService.getById(addressId);
	}

}
