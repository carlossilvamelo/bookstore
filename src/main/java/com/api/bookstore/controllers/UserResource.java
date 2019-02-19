package com.api.bookstore.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.api.bookstore.dto.UserDto;
import com.api.bookstore.models.User;
import com.api.bookstore.services.UserService;

@RestController
@RequestMapping("/users")
public class UserResource {

	@Autowired
	private UserService userService;

	@PostMapping
	public User signup(@Valid @RequestBody UserDto userDto) {
		return userService.signup(userDto);
	}

	@GetMapping
	public List<User> getAll(@RequestParam(defaultValue = "") String name,
			@RequestParam(defaultValue = "0") String pageNumber, @RequestParam(defaultValue = "10") String pageSize) {

		return userService.getAllWithFilter(name, pageNumber, pageSize).getContent();
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public User getById(@PathVariable Long id) {
		return userService.getById(id);
	}

	@DeleteMapping("/{userId}")
	@PreAuthorize("hasRole('ADMIN')")
	public User delete(@PathVariable Long userId) {
		return userService.removeById(userId);
	}

}
