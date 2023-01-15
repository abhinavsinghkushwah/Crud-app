package com.crud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.model.User;
import com.crud.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	public UserService userService;

	@GetMapping
	public List<User> findall() {
		return userService.findAll();
	}

	@PostMapping("/add")
	public void addUser(@RequestBody User user) {
		userService.addUser(user);

	}

	@GetMapping("/{id}")
	public Optional<User> findbyId(@PathVariable int id) {
		return userService.findById(id);
	}


	@PutMapping("/update")
	public Optional<User> update(@RequestBody User user) {
		Optional<User> optUser = userService.update(user);
		if (optUser.isPresent()) {
			return optUser;
		}
		return Optional.empty();
	}
}
