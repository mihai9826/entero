package com.mihaimadan.entero.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mihaimadan.entero.dao.DermatoDAO;
import com.mihaimadan.entero.entity.Appointment;

@Service
public class DermatoServiceImpl implements DermatoService {
	
	@Autowired
	private DermatoDAO dermatoDAO;

	@Override
	@Transactional("dermatoTx")
	public List<Appointment> findBySSN(String ssn) {
		
		return dermatoDAO.findBySSN(ssn);
	}

}
