package org.com.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.com.dao.AppointmentRepositories;
import org.com.exception.RecordNotFoundException;
import org.com.model.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class AppointmentService {
	
	@Autowired
	AppointmentRepositories appointmentDao;
	
	public List<Appointment> getAllAppointments(){
		return appointmentDao.findAll();
	}
	
	public ResponseEntity<?> searchAppointment(int appointmentid) {
		Optional<Appointment> findById=appointmentDao.findById(appointmentid);
		try {
			if(findById.isPresent()) {
				Appointment appointment=findById.get();
				return new ResponseEntity<Appointment>(appointment,HttpStatus.OK);
			}
			else
				throw new RecordNotFoundException("Record not found");
		}
		catch(RecordNotFoundException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	public Appointment saveAppointment(Appointment appointment) {
				return appointmentDao.save(appointment);
	}
	
	public String removeAppointment(int appointmentid) {
		Optional<Appointment> findById=appointmentDao.findById(appointmentid);
		if(findById.isPresent()) {
			appointmentDao.deleteById(appointmentid);
			return "Appointment removed";
		}
		
		return "Appointment not present";
	}
	
	public ResponseEntity<Appointment> updateAppointment(Appointment appoint) {

        Optional<Appointment> findById = appointmentDao.findById(appoint.getAppointmentId());
        try {
            if (findById.isPresent()) {
            	appointmentDao.save(appoint);
                return new ResponseEntity<Appointment>(appoint, HttpStatus.OK);
            	} 
            else {
                throw new RecordNotFoundException("Appointment not present");
            }
        }
        catch (RecordNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
