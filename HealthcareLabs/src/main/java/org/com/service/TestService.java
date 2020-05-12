package org.com.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.com.dao.DiagnosticCentreRepositories;
import org.com.dao.TestRepositories;
import org.com.exception.RecordNotFoundException;
import org.com.model.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class TestService {
	
	@Autowired
	TestRepositories testDao;
	
	@Autowired
	DiagnosticCentreRepositories diagnosticCentreDao;
	
	
	public Test addTestIntoExistingDiagnosticCentre(Integer diagnosticCentreId,Test test) {
		test.setDiagnosticCentre(diagnosticCentreDao.findById(diagnosticCentreId).get());
		return testDao.save(test);
	}
	
	
	public List<Test> viewAllTests(){
		return testDao.findAll();
		}
	
	public Test addTest(Test test) {
		return testDao.save(test);
	}
	
	public ResponseEntity<Test> updateTest(Test test){
		Optional<Test> findById = testDao.findById(test.getTestId());
        try {
            if (findById.isPresent()) {
                testDao.save(test);
                return new ResponseEntity<Test>(test, HttpStatus.OK);
            	} 
            else {
                throw new RecordNotFoundException("Test not present");
            }
        }
        catch (RecordNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
	}
	
	public String deleteTest(Integer tid) {
		Optional<Test> findById=testDao.findById(tid);
		if(findById.isPresent()) {
			testDao.deleteById(tid);
			return "Test removed";
		}
		return "Test not present";
	}
	
	public ResponseEntity<?> searchTestById(Integer tid) {
		Optional<Test> findById=testDao.findById(tid);
		try {
			if(findById.isPresent()) {
				Test test=findById.get();
				return new ResponseEntity<Test>(test,HttpStatus.OK);
			}
			else
				throw new RecordNotFoundException("Test not found");
		}
		catch(RecordNotFoundException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
}
