package org.com.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.com.dao.DiagnosticCentreRepositories;
import org.com.dao.TestRepositories;
import org.com.exception.RecordNotFoundException;
import org.com.model.DiagnosticCentre;
import org.com.model.Test;
import org.com.service.DiagnosticCentreService;
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
@RequestMapping("/diagnosticCentre")
@CrossOrigin("http://localhost:4200")
public class DiagnosticCentreController {
	
	@Autowired
	DiagnosticCentreRepositories dao;
	
	@Autowired
	DiagnosticCentreService diagnosticCentreService;
	
	@Autowired
	TestRepositories testDao;
	
	@RequestMapping("/allDiagnosticCentres")
	public List<DiagnosticCentre> getAllDiagnosticCentres(){
		return diagnosticCentreService.getAllDiagnosticCentres();
	}
	
	@RequestMapping("/searchDiagnosticCentre/{id}")
	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<?> findDiagnosticCentre(@PathVariable("id") int diagnosticCentreid) {
		return diagnosticCentreService.searchDiagnosticCentre(diagnosticCentreid);
	}
	
	@PostMapping("/addDiagnosticCentre")
	@ExceptionHandler(RecordNotFoundException.class)
	public DiagnosticCentre saveDiagnosticCentre(@RequestBody DiagnosticCentre diagnosticCentre) {
		return diagnosticCentreService.saveDiagnosticCentre(diagnosticCentre);
	}
	
	@DeleteMapping("/deleteDiagnosticCentre/{id}")
	public String removeDiagnosticCentre(@PathVariable("id") int diagnosticCentreid) {
		return diagnosticCentreService.removeDiagnosticCentre(diagnosticCentreid);
	}
	
	@PutMapping("/updateDiagnosticCentre")
    public ResponseEntity<DiagnosticCentre> updateDiagnosticCentre(@Valid @RequestBody DiagnosticCentre diagnosticCentre) {
		return diagnosticCentreService.updateDiagnosticCentre(diagnosticCentre);
    }

}


//@RequestMapping("/searchDiagnosticCentre/{id}")
//public DiagnosticCentre findDiagnosticCentre(@PathVariable("id") int diagnosticCentreid) {
//	Optional<DiagnosticCentre> findById=dao.findById(diagnosticCentreid);
//	if(findById.isPresent())
//		return findById.get();
//	
//	return null;
//}

//@PostMapping("/addAppointment")
//public String saveAppointment(@RequestBody Appointment appointment1) {
//	Optional<Appointment> findById=dao.findById(appointment1.getAppointmentId());
//	if(!findById.isPresent()) {
//		dao.save(appointment1);
//		return "appointment added";
//	}
//	
//	return "appointment already exists";
//}

//@PutMapping("/updateDiagnosticCentre")
//public String updateDiagnosticCentre(@RequestBody DiagnosticCentre diagnosticCentre) {
//	Optional<DiagnosticCentre> findById=dao.findById(diagnosticCentre.getDiagnosticCentreId());
//	if(findById.isPresent()) {
//		dao.save(diagnosticCentre);
//		return "Diagnostic Centre updated";
//	}
//	
//	return "Diagnostic Centre not present";
//}
