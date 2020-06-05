package com.sebas.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sebas.springboot.dao.IClienteDao;
import com.sebas.springboot.entity.Person;

@Service
public class ClienteServiceImpl implements IClienteService{

	@Autowired
	private IClienteDao personDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Person> findAll() {
		return (List<Person>) personDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Person findById(Long id) {
		return personDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Person save(Person cliente) {
		return personDao.save(cliente);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		personDao.deleteById(id);
		
	}



}
