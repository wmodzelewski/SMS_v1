package com.wmodzelewski.repository;

import org.springframework.data.repository.CrudRepository;

import com.wmodzelewski.model.Person;

public interface PersonRepository extends CrudRepository<Person, String> {

}
