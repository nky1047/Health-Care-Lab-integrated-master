package org.com.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.com.dao.AppointmentRepositories;
import org.com.exception.RecordNotFoundException;
import org.com.model.Appointment;
import org.com.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appoint")
@CrossOrigin("http://localhost:4200")
public class AppointmentController {
	
	@Autowired
	AppointmentService appointmentService;
	
	@Autowired
	AppointmentRepositories dao;
	
	@RequestMapping("/allAppointments")
	public List<Appointment> getAllAppointments(){
		return appointmentService.getAllAppointments();
	}
	
	@RequestMapping("/searchAppointment/{id}")
	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<?> findAppointment1(@PathVariable("id") int appointmentid) {
		return appointmentService.searchAppointment(appointmentid);
	}
	
	@PostMapping("/addAppointment")
	@ExceptionHandler(RecordNotFoundException.class)
	public Appointment saveAppointment(@RequestBody Appointment appointment) {
		return appointmentService.saveAppointment(appointment);
	}
	
	@DeleteMapping("/deleteAppointment/{id}")
	public String removeAppointment(@PathVariable("id") int appointmentid) {
		return appointmentService.removeAppointment(appointmentid);
	}
	
	@PutMapping("/updateAppointment")
    public ResponseEntity<Appointment> updateAppointment(@Valid @RequestBody Appointment appoint) {
		return appointmentService.updateAppointment(appoint);
    }

}