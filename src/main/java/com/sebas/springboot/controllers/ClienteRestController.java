package com.sebas.springboot.controllers;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sebas.springboot.entity.Person;
import com.sebas.springboot.services.IClienteService;


/**
 * @author sebas_000
 *
 */
@CrossOrigin(origins = { 
		"http://localhost:4200"
})
@RestController
@RequestMapping("/api")
public class ClienteRestController {

	@Autowired(required = true)
	private IClienteService personService;

	@GetMapping("/clientes")
	public List<Person> index() {
		return personService.findAll();
	}

	@GetMapping("/clientes/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Person person = null;
		
		Map<String, Object> respoMap = new HashMap<String, Object>();
		
		try {
			person = personService.findById(id);
		} catch (DataAccessException e) {
			respoMap.put("mensaje", "Error");
			return new ResponseEntity< Map<String,Object> >(respoMap,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (person != null) {
			return new ResponseEntity<Person>(person,HttpStatus.OK);
		} else {
			respoMap.put("mensaje", "Persona: "+id+" No encontrada");
			return new ResponseEntity< Map<String,Object> >(respoMap,HttpStatus.NOT_FOUND);
		}
		
		
		
		
	}
	
	@PostMapping("/clientes")
	public ResponseEntity<?> create(@RequestBody Person person) {
		
		Person personNew = null;
		person.setCreateAt(new Date());
		
		Map<String, Object> respoMap = new HashMap<String, Object>();
		
		try {
				personNew = personService.save(person);
		} catch (DataAccessException e) {
			respoMap.put("mensaje", "Error");
			respoMap.put("error", "Error del insert : "+e.getMessage()+" : "+e.getMostSpecificCause());
			
			return new ResponseEntity< Map<String, Object> >(respoMap, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		respoMap.put("mensaje", "Persona creada con exito!");
		respoMap.put("cliente", personNew);
		return new ResponseEntity< Map<String, Object> >(respoMap, HttpStatus.CREATED);
	}
	
	@PutMapping("/clientes/{id}")
	public ResponseEntity<?> update(@RequestBody Person person, @PathVariable Long id) {
		
		Person personAct = personService.findById(id);
		Person personUpdated = null;
		
		Map<String, Object> respoMap = new HashMap<String, Object>();
		
		if (personAct == null) {
			respoMap.put("Mensaje", "Persona no encontrado, el ID "+id+" no existe en la base de datos");
			return new ResponseEntity< Map<String, Object> >(respoMap, HttpStatus.NOT_FOUND);
		}
		try {
			personAct.setfullName(person.getfullName());
			personUpdated = personService.save(personAct);
		} catch (DataAccessException e) {
			respoMap.put("error", "error: "+e.getMessage()+" DETALLES: "+e.getMostSpecificCause().toString());
			return new ResponseEntity< Map<String, Object> >(respoMap, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		respoMap.put("mensaje", "Persona Actualizado");
		respoMap.put("cliente:",personUpdated);
		return new ResponseEntity< Map<String, Object> >(respoMap, HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		Map<String, Object> respoMap = new HashMap<String, Object>();
		
		try {
			personService.delete(id);
		} catch (DataAccessException e) {
			respoMap.put("mensaje", "Error en la eliminacion");
			respoMap.put("error", e.getMessage()+": "+e.getMostSpecificCause().getMessage());
			return new ResponseEntity< Map<String, Object> >(respoMap, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		respoMap.put("mensaje","cliente eliminado con exito");
		return new ResponseEntity< Map<String, Object> >(respoMap, HttpStatus.OK);
				
		
		
	}
}
