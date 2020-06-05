package com.sebas.springboot.dao;

import org.springframework.data.repository.CrudRepository;

import com.sebas.springboot.entity.Person;

public interface IClienteDao extends CrudRepository<Person, Long>{

}
