package com.wmodzelewski.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "person")
public class Person {
	@Id
	@Column(name = "id")
	String id;
	@Column(name = "firstname")
	String firstName;
	@Column(name = "secondname")
	String secondName;
	@Column(name = "currentprojectid")
	String currentProjectId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
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
