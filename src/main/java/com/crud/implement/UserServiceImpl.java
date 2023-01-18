package com.crud.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.crud.model.User;
import com.crud.repository.UserRepository;
import com.crud.service.UserService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	@Autowired
	UserRepository userRepository;
	@Autowired
	PasswordEncoder encoder;
	
	@Override
	public List<User> findAll() {
		return userRepository.findAll();
		
	}
	@Override
	public void addUser(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
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
	 

	public Optional<User> update(User user) {
		Optional<User> useropt=userRepository.findById( user.getId());
		if(useropt.isPresent()) {
			User existingUser=useropt.get();
			
			if(existingUser.getFirstName()!=null) {
				existingUser.setFirstName(user.getFirstName());
			}
			if(existingUser.getLastName()!=null) {
				existingUser.setLastName(user.getLastName());
			}
			if(existingUser.getAge()!= null) {
				existingUser.setAge(user.getAge());
			}
			if(existingUser.getCountry()!=null) {
				existingUser.setCountry(user.getCountry());
			}
			if(existingUser.getAge()!=null) {
				existingUser.setAge(user.getAge());
			}
			if(existingUser.getUsername()!=null) {
				existingUser.setUsername(null);
			}
			if(existingUser.getPassword()!=null) {
				existingUser.setPassword(encoder.encode(user.getPassword()));
			}
			userRepository.save(existingUser);
		return Optional.of(existingUser);
		}
		return Optional.empty();
		
		
	}
	public List<User> findByCriteria(String criteria, String searchitem){
		switch(criteria) {
		case "username":
			return userRepository.findByUsername(searchitem);
		case "firstName":
			return userRepository.findByFirstName(searchitem);
		case "lastName":
			return userRepository.findByLastName(searchitem);
		case "age":
			try {
				Integer age= Integer.valueOf(searchitem);
				return userRepository.findByAge(age);
			
			}
			catch(NumberFormatException e) {
				System.out.println("nope, cant do");
				
			}
			return new ArrayList<>();
		case "country":
			return userRepository.findByCountry(searchitem);
		}
		return new ArrayList<>();
	}

}
