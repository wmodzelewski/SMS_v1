package com.cgi.vaadin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "person2")
public class Person {

	@Id
	@SequenceGenerator(name = "id_seq", sequenceName = "id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq")
	@Column(name = "id", updatable = false)
	int id;
	@Column(name = "firstname")
	String firstName;
	@Column(name = "secondname")
	String secondName;
	@Column(name = "currentprojectid")
	String currentProjectId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getCurrentProjectId() {
		return currentProjectId;
	}

	public void setCurrentProjectId(String currentProjectId) {
		this.currentProjectId = currentProjectId;
	}
}
