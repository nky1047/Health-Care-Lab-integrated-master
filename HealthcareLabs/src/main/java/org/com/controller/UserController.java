package org.com.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.com.dao.UserRepository;
import org.com.exception.RecordNotFoundException;
import org.com.model.Admin;
import org.com.model.User;
import org.com.service.UserService;
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
public class UserController {
	@Autowired
	UserService service;

	// SHOW ALL USERS
	@GetMapping("/getUser")
	public List<User> getAllUsers() {
		return service.getAllUsers();
	}

	// SEARCH USER
	@RequestMapping("/getUser/{id}")
	public ResponseEntity<User> findUser(@PathVariable("id") int uid) {
		return service.findUser(uid);
	}

	// Add USER
	@PostMapping("/getUser")
	public ResponseEntity<User> saveUser(@RequestBody User user) {
		return service.saveUser(user);
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
	public String updateProduct(@Valid @RequestBody User user) {
		return service.updateUser(user);
	}
}
