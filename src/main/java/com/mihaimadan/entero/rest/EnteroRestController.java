package com.mihaimadan.entero.rest;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.mihaimadan.entero.entity.Appointment;
import com.mihaimadan.entero.entity.Appointments;
import com.mihaimadan.entero.entity.Patient;
import com.mihaimadan.entero.service.PatientService;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
public class EnteroRestController {
	
	@Autowired
	private PatientService patientService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	Logger logger = LoggerFactory.getLogger(EnteroRestController.class);
	
	@GetMapping("/patients")
	public List<Patient> getAllPatients(@RequestParam(required=false) String date) {
		
		if(date != null) {
			Appointments appoints = 
					restTemplate.getForObject("http://dermato-service/api/appointments/?date=" + date, Appointments.class);
			if(appoints.getPatientAppointments().isEmpty()) {
				throw new PatientNotFoundException("Appointments not found");
			
			}
			List<Patient> thePatients = new ArrayList<>();
			
			for(Appointment temp : appoints.getPatientAppointments()) {
				Patient patient = patientService.getPatient(temp.getSSN());
				
				patient.setAppointment(temp);
				
				thePatients.add(patient);
			}
			return thePatients;
		}
		
		List<Patient> thePatients = patientService.getPatients();
		
		for(Patient temp : thePatients) {
			Appointments appoints = restTemplate.getForObject("http://dermato-service/api/appointments/" + temp.getSSN(), Appointments.class);
			temp.setAppointments(appoints);
		}
		
		return thePatients;
	}
	
	@GetMapping("/patients/{patientId}")
	public Patient getPatientById(@PathVariable String patientId) {
		
		Patient thePatient = patientService.getPatient(patientId);
		
		if (thePatient == null) {
			throw new PatientNotFoundException("Patient with id: " + patientId + " not found");
		}
		
		Appointments appoints = restTemplate.getForObject("http://dermato-service/api/appointments/" + patientId, Appointments.class);
		
		thePatient.setAppointments(appoints);
		
		return thePatient;
	}
	
	@PostMapping("/patients")
	public Patient addNewPatient(@RequestBody Patient newPatient) throws Exception {
		
		List<Patient> thePatients = patientService.getPatients();
		
		if(thePatients.contains(newPatient)) {
			throw new Exception("Patient already exists");
		}
		
		patientService.addPatient(newPatient);
		
		
		return newPatient;
	}
	
	@PutMapping("/patients")
	public Patient updatePatient(@RequestBody Patient thePatient) {
		
		patientService.addPatient(thePatient);
		
		return thePatient;
	}
	

}
