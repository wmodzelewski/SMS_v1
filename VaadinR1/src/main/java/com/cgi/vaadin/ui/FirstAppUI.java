package com.cgi.vaadin.ui;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import com.cgi.vaadin.model.Person;
import com.cgi.vaadin.repository.PersonRepository;
import com.vaadin.annotations.Theme;
import com.vaadin.data.ValidationException;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SpringUI
@Theme("t1_vaadintheme")
public class FirstAppUI extends UI {

	private HorizontalLayout mainLayout;

	@Autowired
	private PersonRepository personRepository;

	private VerticalLayout vl;

	PersonDetails personDetails;

	Grid<Person> gridPerson;

	@Override
	protected void init(VaadinRequest request) {
		mainLayout = new HorizontalLayout();
		vl = new VerticalLayout();
		Label header = new Label("Vaadin demo project");
		header.addStyleName(ValoTheme.LABEL_H1);
		vl.addComponent(header);
		vl.setComponentAlignment(header, Alignment.TOP_CENTER);
		vl.addComponent(mainLayout);
		setContent(vl);
		initialize();
	}

	private void initialize() {
		gridPerson = createGrid();

		personDetails = new PersonDetails();
		personDetails.setPerson(new Person());

		VerticalLayout details = new VerticalLayout();
		details.setMargin(false);

		details.addComponents(personDetails, createButtons());
		details.setDefaultComponentAlignment(Alignment.TOP_CENTER);

		mainLayout.addComponent(details);

	}

	private Grid<Person> createGrid() {
		gridPerson = new Grid<>();
		gridPerson.addColumn(Person::getFirstName).setCaption("First Name");
		gridPerson.addColumn(Person::getSecondName).setCaption("Second Name");
		gridPerson.addColumn(Person::getCurrentProjectId).setCaption("Project");

		gridPerson.setItems((Collection<Person>) personRepository.findAll());
		mainLayout.addComponent(gridPerson);

		gridPerson.addSelectionListener(listener -> {
			if (listener.getFirstSelectedItem().isPresent()) {
				personDetails.setPerson(listener.getFirstSelectedItem().get());
			}
		});

		return gridPerson;
	}

	private HorizontalLayout createButtons() {
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

		HorizontalLayout buttons = new HorizontalLayout();
		buttons.addComponents(save, createNew);
		return buttons;
	}

}
