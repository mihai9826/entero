package com.mihaimadan.entero.rest;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mihaimadan.entero.entity.Patient;
import com.mihaimadan.entero.service.PatientService;

@RestController
public class EnteroRestController {
	
	@Autowired
	private PatientService patientService;
	
	@GetMapping("/patients")
	public List<Patient> getAllPatients() {
		
		List<Patient> thePatients = patientService.getPatients();
		
		return thePatients;
	}
	
	@GetMapping("/patients/{patientId}")
	public Patient getPatientById(@PathVariable String patientId) {
		
		Patient thePatient = patientService.getPatient(patientId);
		
		if (thePatient == null) {
			throw new PatientNotFoundException("Patient with id: " + patientId + " not found");
		}
		
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
