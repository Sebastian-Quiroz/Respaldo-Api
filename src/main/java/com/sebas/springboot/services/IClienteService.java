package com.sebas.springboot.services;

import java.util.List;

import com.sebas.springboot.entity.Person;


public interface IClienteService {
	
public List<Person> findAll();
	
	public Person findById(Long id);
	
	public Person save(Person cliente);
	
	public void delete(Long id);
	
}
