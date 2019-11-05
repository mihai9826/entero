package com.mihaimadan.entero.service;

import java.util.List;

import com.mihaimadan.entero.entity.Patient;

public interface PatientService {
	
	public List<Patient> getPatients();
	
	public Patient getPatient(String id);
	
	public void addPatient(Patient thePatient);

}
