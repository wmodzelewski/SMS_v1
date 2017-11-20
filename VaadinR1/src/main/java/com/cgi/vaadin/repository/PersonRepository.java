package com.cgi.vaadin.repository;

import org.springframework.data.repository.CrudRepository;

import com.cgi.vaadin.model.Person;

public interface PersonRepository extends CrudRepository<Person, String> {

}
