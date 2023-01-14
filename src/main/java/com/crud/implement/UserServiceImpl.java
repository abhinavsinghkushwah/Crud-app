package com.crud.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.model.User;
import com.crud.repository.UserRepository;
import com.crud.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserRepository userRepository;
	
	@Override
	public List<User> findAll() {
		return userRepository.findAll();
		
	}
	@Override
	public void addUser(User user) {
		userRepository.save(user);
	}
	
	public Optional<User> delete(int id) {
		Optional<User>userOpt=userRepository.findById(id);
		
		if(userOpt.isPresent()) {
			
			userRepository.delete(userOpt.get());
		}
		return Optional.empty();
	}
	public Optional<User> findById(int id){
	 return userRepository.findById(id);
	 
	
	}


}
