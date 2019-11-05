package com.mihaimadan.entero.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mihaimadan.entero.dao.PatientDAO;
import com.mihaimadan.entero.entity.Patient;

@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
	private PatientDAO patientDAO;
	
	
	@Override
	@Transactional
	public List<Patient> getPatients() {
		
		return patientDAO.getPatients();
	}

	@Override
	@Transactional
	public Patient getPatient(String id) {
		
		return patientDAO.getPatient(id);
	}

	@Override
	@Transactional
	public void addPatient(Patient thePatient) {
		
		patientDAO.addPatient(thePatient);
	}
	
	

}
