package com.mihaimadan.entero.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mihaimadan.entero.entity.Patient;

@Repository
public class PatientDAOImpl implements PatientDAO {
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Patient> getPatients() {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<Patient> theQuery = 
				currentSession.createQuery("From Patient", Patient.class);
		
		List<Patient> patients = theQuery.getResultList();
		
		return patients;
	}

	@Override
	public Patient getPatient(String id) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		Patient thePatient = currentSession.get(Patient.class, id);
		
		return thePatient;
	}

	@Override
	public void addPatient(Patient thePatient) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		currentSession.saveOrUpdate(thePatient);
	}

}
