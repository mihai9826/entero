package com.mihaimadan.entero.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.mihaimadan.entero.entity.Patient;

@Repository
public class PatientDAOImpl implements PatientDAO {
	
	@Autowired
	@Qualifier("entero")
	private SessionFactory sessionFactory;

	@Override
	public List<Patient> getPatients() {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Patient> theQuery = 
				currentSession.createQuery("From Patient", Patient.class);
		
		List<Patient> patients = theQuery.getResultList();
		
		return patients;
	}

	@Override
	public Patient getPatient(String id) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Patient thePatient = currentSession.get(Patient.class, id);
		
		return thePatient;
	}

	@Override
	public void addPatient(Patient thePatient) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.saveOrUpdate(thePatient);
	}

}
