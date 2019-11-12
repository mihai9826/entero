package com.mihaimadan.entero.service;

import java.util.List;

import com.mihaimadan.entero.entity.Appointment;

public interface DermatoService {
	
	public List<Appointment> findBySSN(String ssn);

}
