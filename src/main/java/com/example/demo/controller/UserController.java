package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.service.ServiceImplementations;


@RestController
@RequestMapping("users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	
	private ServiceImplementations serimp;
	
	public UserController(ServiceImplementations serimp) {
			this.serimp = serimp;
		}
		
		@GetMapping("/list")
		public List<User> displayAllUser(){
			List<User> userList = serimp.DisplayAllUsers();
			return userList;
		}
		
		@GetMapping("/list/{id}")
		public User displayStypeById(@PathVariable("id") int id){
			User user = serimp.getUserById(id);
			return user;
		}
		
		@PostMapping("/list")
		public void insertUser(@RequestBody User user) {
			serimp.insertUser(user);
			
		}
	
		@DeleteMapping("/list/{id}")
		public void DeleteUser(@PathVariable("id") int id) {
			serimp.deleteUser(id);
		}
		
		@PutMapping("/list")
		public void updateUser(@RequestBody User user) {
			serimp.updateUser(user);
		}
		
		@GetMapping("/list1/{uname}")
		public User getByname(@PathVariable("uname") String uname) {
			User user = serimp.getUserByName(uname);
			return user;
		}
}

