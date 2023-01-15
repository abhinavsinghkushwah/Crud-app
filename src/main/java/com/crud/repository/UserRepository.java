package com.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.crud.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	
//	public List<User> findByfirstName(String firstName);
//	public List<User> findByLastName(String lastName);
//	public List<User> findByCountry(String Country);
//	public List<User> findByAge(int age);
}
