package org.com.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.com.dao.DiagnosticCentreRepositories;
import org.com.dao.TestRepositories;
import org.com.exception.RecordNotFoundException;
import org.com.model.DiagnosticCentre;
import org.com.model.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class DiagnosticCentreService {
	
	@Autowired
	DiagnosticCentreRepositories diagnosticCentreDao;
	
	@Autowired
	TestRepositories testDao;
	
	public List<DiagnosticCentre> getAllDiagnosticCentres(){
		return diagnosticCentreDao.findAll();
	}
	
	public ResponseEntity<?> searchDiagnosticCentre(int diagnosticCentreid) {
		Optional<DiagnosticCentre> findById=diagnosticCentreDao.findById(diagnosticCentreid);
		try {
			if(findById.isPresent()) {
				DiagnosticCentre diagnosticCentre=findById.get();
				return new ResponseEntity<DiagnosticCentre>(diagnosticCentre,HttpStatus.OK);
			}
			else
				throw new RecordNotFoundException("Diagnostic Centre not found");
		}
		catch(RecordNotFoundException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	public DiagnosticCentre saveDiagnosticCentre(DiagnosticCentre diagnosticCentre) {
		Set<Test> set=diagnosticCentre.getSetOfTests();
		diagnosticCentre.setSetOfTests(set);
		DiagnosticCentre i = diagnosticCentreDao.save(diagnosticCentre);

		for (Test p : i.getSetOfTests()) {
			testDao.save(p);
		}
		return i;
	}
	
	public String removeDiagnosticCentre(int diagnosticCentreid) {
		Optional<DiagnosticCentre> findById=diagnosticCentreDao.findById(diagnosticCentreid);
		if(findById.isPresent()) {
			diagnosticCentreDao.deleteById(diagnosticCentreid);
			return "Diagnostic Centre removed";
		}
		
		return "Diagnostic Centre not present";
	}
	
	public ResponseEntity<DiagnosticCentre> updateDiagnosticCentre(DiagnosticCentre diagnosticCentre) {

        Optional<DiagnosticCentre> findById = diagnosticCentreDao.findById(diagnosticCentre.getDiagnosticCentreId());
        try {
            if (findById.isPresent()) {
            	Set<Test> set=diagnosticCentre.getSetOfTests();
        		diagnosticCentre.setSetOfTests(set);
        		DiagnosticCentre i = diagnosticCentreDao.save(diagnosticCentre);

        		for (Test p : i.getSetOfTests()) {
        			testDao.save(p);
        		}
                return new ResponseEntity<DiagnosticCentre>(diagnosticCentre, HttpStatus.OK);
            	} 
            else {
                throw new RecordNotFoundException("Diagnostic Centre not present");
            }
        }
        catch (RecordNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
