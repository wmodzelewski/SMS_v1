package com.wmodzelewski.ui;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.wmodzelewski.model.Person;
import com.wmodzelewski.repository.PersonRepository;

@SpringUI
public class VaadinStarter extends UI {

	@Autowired
	PersonRepository personRepository;

	@Override
	protected void init(VaadinRequest request) {
		HorizontalLayout hl = new HorizontalLayout();
		VerticalLayout vl = new VerticalLayout();
		setContent(vl);
		vl.addComponent(hl);

		Grid<Person> personGrid = new Grid<>(Person.class);
		personGrid.setItems((Collection<Person>) personRepository.findAll());
		hl.addComponent(personGrid);
	}

}
