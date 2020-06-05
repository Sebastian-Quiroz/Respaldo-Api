package com.sebas.springboot.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "persons")
public class Person implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false,name = "fullname", unique = true)
	private String fullName;
	

	@Column(name="birth")
	@Temporal(TemporalType.DATE)
	private Date birth;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getfullName() {
		return fullName;
	}

	public void setfullName(String nombre) {
		this.fullName = nombre;
	}

	public Date getCreateAt() {
		return birth;
	}

	public void setCreateAt(Date createAt) {
		this.birth = createAt;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
