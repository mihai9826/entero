package com.mihaimadan.entero.dao;

import java.util.List;

import com.mihaimadan.entero.entity.Appointment;

public interface DermatoDAO {
	
	public List<Appointment> findBySSN(String ssn);

}
