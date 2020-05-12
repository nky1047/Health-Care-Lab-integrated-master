package org.com.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.com.dao.TestRepositories;
import org.com.exception.RecordNotFoundException;
import org.com.model.Appointment;
import org.com.model.Test;
import org.com.service.TestService;
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
@RequestMapping("/test")
@CrossOrigin("http://localhost:4200")
public class TestController {
	@Autowired
	TestRepositories dao;
	
	@Autowired
	private TestService testService;
	
	// add Test Into Existing Diagnostic Centre
	@PostMapping("/addTest/{diagnosticCentreId}")
	public Test addTestIntoExistingDiagnosticCentre(@PathVariable(value = "diagnosticCentreId") Integer diagnosticCentreId, @RequestBody Test test) {
		return testService.addTestIntoExistingDiagnosticCentre(diagnosticCentreId, test);
	}
			
	
	@RequestMapping("/allTests")
	public List<Test> getAllTests(){
		return testService.viewAllTests();
	}
	
	@PostMapping("/addTest")
    public Test saveTest(@Valid @RequestBody Test test) {
		return  testService.addTest(test);
    }
	
	@RequestMapping("/searchTest/{id}")
	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<?> findTest(@PathVariable("id") Integer tid) {
		return testService.searchTestById(tid);
	}
	
	@DeleteMapping("/deleteTest/{id}")
	public String removeTest(@PathVariable("id") Integer tid) {
		return testService.deleteTest(tid);
	}
	
	@PutMapping("/updateTest")
    public ResponseEntity<Test> updateTest(@Valid @RequestBody Test test) {
		return testService.updateTest(test);
    }
	
}

//@PostMapping("/addTest")
//@ExceptionHandler(RecordNotFoundException.class)
//	public ResponseEntity<Test> saveTest(@RequestBody Test test) {
//	Optional<Test> findById=dao.findById(test.getTestId());
//	try{
//		if(!findById.isPresent()) {
//			dao.save(test);
//			return new ResponseEntity<Test>(test, HttpStatus.OK);
//		}
//		else
//			throw new RecordNotFoundException("Test already present...");
//	}
//	catch(RecordNotFoundException e){
//		return new ResponseEntity<Test>(test, HttpStatus.NOT_FOUND);
//	}
//}


//@PutMapping("/updateTest")
//public String updateTest(@RequestBody Test test) {
//	Optional<Test> findById=dao.findById(test.getTestId());
//	if(findById.isPresent()) {
//		dao.save(test);
//		return "Test updated";
//	}
//	return "Test not present";
//}
