package com.mihaimadan.entero.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.mihaimadan.entero.entity.Appointment;

@Repository
public class DermatoDAOImpl implements DermatoDAO {
	
	@Autowired
	@Qualifier("dermato")
	private SessionFactory sessionFactory;

	@Override
	public List<Appointment> findBySSN(String ssn) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Appointment> theQuery = 
				currentSession.createQuery("from Appointment where ssn=:ssnId",
						Appointment.class);
		
		theQuery.setParameter("ssnId", ssn);
		
		return theQuery.getResultList();
	}

}
