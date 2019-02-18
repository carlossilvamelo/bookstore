package com.api.bookstore.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.bookstore.dto.LoginDto;
import com.api.bookstore.dto.UserDto;
import com.api.bookstore.models.Credential;
import com.api.bookstore.models.User;
import com.api.bookstore.services.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user")
@Api(value = "LibraryAPI", description = "Library API")
public class UserResource {

	@Autowired
	private UserService userService;


	@PostMapping("")
	public User create(@Valid @RequestBody User user) {
		return userService.create(user);
	}

	@PostMapping("{userId}")
	public User signup(@PathVariable Long userId, @Valid @RequestBody Credential credential) {
		return userService.signup(userId, credential);
	}

	@GetMapping("")
	public List<User> getAll(@RequestParam(defaultValue = "") String name,
			@RequestParam(defaultValue = "0") String page
			, @RequestParam(defaultValue = "10") String size) {
		Pageable pageRequest = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size));
		
		return userService.getByParam(name, pageRequest);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public User getById(@PathVariable Long id) {
		return userService.getById(id);
	}

	@DeleteMapping("{userId}")
	@PreAuthorize("hasRole('ADMIN')")
	public User delete(@PathVariable Long userId) {
		return userService.removeById(userId);
	}


}
