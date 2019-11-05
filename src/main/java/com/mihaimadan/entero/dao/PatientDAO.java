package com.mihaimadan.entero.dao;

import java.util.List;

import com.mihaimadan.entero.entity.Patient;

public interface PatientDAO {
	
	public List<Patient> getPatients();
	
	public Patient getPatient(String id);
	
	public void addPatient(Patient thePatient);

}
