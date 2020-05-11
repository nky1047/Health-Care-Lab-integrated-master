package org.com.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.com.dao.UserRepository;
import org.com.exception.RecordNotFoundException;
import org.com.model.Admin;
import org.com.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UserService {
	@Autowired
	UserRepository dao;

	//SHOW ALL USERS
	public List<User> getAllUsers() {
		return dao.findAll();
	}

	//SEARCH AN USER
	public ResponseEntity<User> findUser(int uid) {
		Optional<User> findById = dao.findById(uid);
		try {
			if (findById.isPresent()) {
				User user = findById.get();
				return new ResponseEntity<User>(user, HttpStatus.OK);
			} else
				throw new RecordNotFoundException("Record not found!!");
		} catch (RecordNotFoundException e) {

			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
	}

	//ADD NEW USER
	public ResponseEntity<User> saveUser(User user) {
		Optional<User> findById = dao.findById(user.getId());
		try {
			if (!findById.isPresent()) {
				dao.save(user);
				return new ResponseEntity<User>(user, HttpStatus.OK);
			} else
				throw new RecordNotFoundException("Record already present...");
		} catch (RecordNotFoundException e) {
			return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
		}
	}

	
	//REMOVE AN USER
	public String removeUser(@PathVariable("id") int uid) {
		Optional<User> findById = dao.findById(uid);
		if (findById.isPresent()) {
			dao.deleteById(uid);
			return "User " + dao.findById(uid) + " Removed!!!";
		}
		return "User Not Found!!!";
	}

	
	//COUNT ALL USERS
	public String getCount() {
		return "Total No of Users are: "+dao.count(); 
	}

	//UPDATE AN USER
	public String updateUser(@RequestBody User user) {
		Optional<User> findById = dao.findById(user.getId());
		if (findById.isPresent()) {
			dao.save(user);
			return "Admin Detail Updated!!!";
		}
		return "Admin Not Found!!!";
	}
	
	
	
}
