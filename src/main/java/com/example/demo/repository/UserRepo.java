package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.User;


public interface UserRepo extends JpaRepository<User, Integer> {

	@Query(value="from User where uname= ?1")
	public User searchUserByname(String uname);
	
}
