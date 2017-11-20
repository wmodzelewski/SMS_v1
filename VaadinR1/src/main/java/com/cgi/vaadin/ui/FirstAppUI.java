package com.cgi.vaadin.ui;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import com.cgi.vaadin.model.Person;
import com.cgi.vaadin.repository.PersonRepository;
import com.vaadin.data.ValidationException;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SpringUI
public class FirstAppUI extends UI {

	private HorizontalLayout mainLayout;

	@Autowired
	private PersonRepository personRepository;

	private VerticalLayout vl;

	@Override
	protected void init(VaadinRequest request) {
		mainLayout = new HorizontalLayout();
		vl = new VerticalLayout();
		vl.addComponent(mainLayout);
		setContent(vl);
		initialize();
	}

	private void initialize() {
		Grid<Person> gridPerson = new Grid<>(Person.class);
		gridPerson.setItems((Collection<Person>) personRepository.findAll());
		mainLayout.addComponent(gridPerson);

		PersonDetails personDetails = new PersonDetails();
		personDetails.setPerson(new Person());

		VerticalLayout details = new VerticalLayout();

		Button save = new Button("Save");
		save.addClickListener(listener -> {
			Person person;
			try {
				person = personDetails.write();
				personRepository.save(person);
				gridPerson.setItems((Collection<Person>) personRepository.findAll());
			} catch (ValidationException e) {
				Notification.show("ERROR \n" + e.getMessage(), Type.ERROR_MESSAGE);
			}

		});

		Button createNew = new Button("New");
		createNew.addClickListener(listener -> {
			Person p = new Person();
			personDetails.setPerson(p);
		});

		details.addComponents(personDetails, save, createNew);

		mainLayout.addComponent(details);

		gridPerson.addSelectionListener(listener -> {
			if (listener.getFirstSelectedItem().isPresent()) {
				personDetails.setPerson(listener.getFirstSelectedItem().get());
			}
		});
	}

}
