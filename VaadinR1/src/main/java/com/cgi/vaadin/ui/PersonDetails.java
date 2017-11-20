package com.cgi.vaadin.ui;

import com.cgi.vaadin.model.Person;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class PersonDetails extends VerticalLayout {

	TextField id = new TextField("id");
	TextField firstName = new TextField("firstName");
	TextField secondName = new TextField("secondName");
	TextField currentProjectId = new TextField("currentProjectId");
	Label status = new Label();
	private Binder<Person> binder = new Binder<>(Person.class);
	Person person = null;

	public PersonDetails() {

		binder.forField(firstName).withValidator(name -> name.length() >= 3, "First Name too short")
				.withStatusLabel(status).bind(Person::getFirstName, Person::setFirstName);

		binder.bindInstanceFields(this);
		addComponents(id, firstName, secondName, currentProjectId, status);
	}

	public void setPerson(Person person) {
		binder.readBean(person);
		this.person = person;
	}

	public Person write() throws ValidationException {
		binder.writeBean(person);
		return person;
	}
}
