package org.com.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.com.dao.User1Repository;
import org.com.exception.RecordNotFoundException;
import org.com.model.Admin;
import org.com.model.User1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class User1Service {
	
	@Autowired
	User1Repository dao;

	// SHOW ALL USERS
	public List<User1> getAllUsers() {
		return dao.findAll();
	}

	// SEARCH AN USER
	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<User1> findUser(int uid) {
		Optional<User1> findById = dao.findById(uid);
		try {
			if (findById.isPresent()) {
				User1 user1 = findById.get();
				return new ResponseEntity<User1>(user1, HttpStatus.OK);
			} else
				throw new RecordNotFoundException("Record not found!!");
		} catch (RecordNotFoundException e) {

			return new ResponseEntity<User1>(HttpStatus.NOT_FOUND);
		}
	}

	// ADD NEW USER
	public User1 saveUser(User1 user1) {
		return dao.save(user1);
	}

	// REMOVE AN USER
	public String removeUser(@PathVariable("id") int uid) {
		Optional<User1> findById = dao.findById(uid);
		if (findById.isPresent()) {
			dao.deleteById(uid);
			return "User " + dao.findById(uid) + " Removed!!!";
		}
		return "User Not Found!!!";
	}

	// COUNT ALL USERS
	public String getCount() {
		return "Total No of Users are: " + dao.count();
	}

	// UPDATE AN USER
	public String updateUser(@RequestBody User1 user1) {
		Optional<User1> findById = dao.findById(user1.getId());
		if (findById.isPresent()) {
			dao.save(user1);
			return "Admin Detail Updated!!!";
		}
		return "Admin Not Found!!!";
	}

}
