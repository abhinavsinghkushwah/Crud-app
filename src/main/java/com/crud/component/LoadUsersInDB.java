package com.crud.component;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.crud.model.User;
import com.crud.repository.UserRepository;

import jakarta.transaction.Transactional;

@Transactional
@Component
public class LoadUsersInDB implements CommandLineRunner {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder asencoder;
	
	public void run(String... args) throws Exception{
		
		if(userRepository.count()>0) {return ;}
		
		User user1=new User("Georgina", "Ortega", "Brazil",21,"gortega",UUID.randomUUID().toString());
		User user2=new User("Garret", "Locka", "Russia",21,"glocka",UUID.randomUUID().toString());
		List<User> lis=Arrays.asList(user1, user2);
		lis=lis.stream().map(user -> {user.setPassword(asencoder.encode(user.getPassword()));
			return user;
		}).collect(Collectors.toList());
		
		
		userRepository.saveAll(lis);
}
}