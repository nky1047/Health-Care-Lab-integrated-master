package org.com.controller;

import java.util.List;

import javax.validation.Valid;

import org.com.dao.AdminRepository;
import org.com.exception.RecordNotFoundException;
import org.com.model.Admin;
import org.com.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/admin")
@CrossOrigin(value = "http://localhost:4200")
public class AdminController {

	@Autowired
	AdminRepository dao;
	
	@Autowired
	AdminService service;


	//Add ADMIN
	@PostMapping("/getAdmin")
	@ExceptionHandler(RecordNotFoundException.class)
	public Admin saveAdmin(@RequestBody Admin admin) {
		return service.saveAdmin(admin);
	}

	// SHOW ALL ADMINS
	@GetMapping("/getAdmin")
	public List<Admin> showAllAdmin() {
		return service.showAllAdmin();
	}
	
	//SEARCH ADMIN
	@RequestMapping("/getAdmin/{id}")
	public ResponseEntity<?> findAdmin(@PathVariable("id") int uid) {
			return service.findAdmin(uid);
	}
	
	//DELETE ADMIN
	@DeleteMapping("/getAdmin/{id}")
	public String removeAdmin(@PathVariable("id") int uid) {
		return service.removeAdmin(uid);
	}

	
	//COUNT ADMINS
	@GetMapping("/getCount")
	public String getTotalCount() {
		return service.getTotalCount();
	}

	
	//UPDATE ADMIN
	@PutMapping("/getAdmin/")
	public ResponseEntity<Admin> updateAdmin(@Valid @RequestBody Admin admin) {
		return service.updateAdmin(admin);
	}


}
