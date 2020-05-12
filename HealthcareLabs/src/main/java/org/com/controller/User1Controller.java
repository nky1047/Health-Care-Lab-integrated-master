package org.com.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.com.dao.User1Repository;
import org.com.exception.RecordNotFoundException;
import org.com.model.Admin;
import org.com.model.User1;
import org.com.service.User1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usr")
@CrossOrigin(value = "http://localhost:4200")
public class User1Controller {
	@Autowired
	User1Repository dao;
	
	@Autowired
	User1Service service;

	// SHOW ALL USERS
	@GetMapping("/getUser")
	public List<User1> getAllUsers() {
		return service.getAllUsers();
	}

	// SEARCH USER
	@RequestMapping("/getUser/{id}")
	public ResponseEntity<User1> findUser(@PathVariable("id") int uid) {
		return service.findUser(uid);
	}

	// Add USER
	@PostMapping("/getUser")
	public User1 saveUser(@RequestBody User1 user1) {
		return service.saveUser(user1);
	}

	// DELETE USER
	@DeleteMapping("/getUser/{id}")
	public String removeUser(@PathVariable("id") int uid) {
		return service.removeUser(uid);
	}

	// COUNT USER
	@GetMapping("/getCount")
	public String getCount() {
		return service.getCount();
	}

	// UPDATE USER
	@PutMapping("/getUser/")
	public String updateProduct(@Valid @RequestBody User1 user1) {
		return service.updateUser(user1);
	}
}
