package com.crud.service;

import java.util.List;
import java.util.Optional;

import com.crud.model.User;


public interface UserService {
	public List<User> findAll();
	public void addUser(User user);
	public Optional<User> findById(int id);
	public Optional<User> delete(int id);
	public Optional<User> update(User user);
	
}
