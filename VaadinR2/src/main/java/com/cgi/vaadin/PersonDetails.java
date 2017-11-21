package com.cgi.vaadin;

import com.cgi.vaadin.model.Person;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;

public class PersonDetails extends FormLayout {
	Person person = null;

	TextField firstName = new TextField("First Name");
	TextField secondName = new TextField("Second Name");
	TextField currentProjectId = new TextField("Project");
	TextField id = new TextField("ID");

	Binder<Person> binder = new Binder(Person.class);

	private Label statusLabel = new Label();;

	public PersonDetails() {
		addComponents(id, firstName, secondName, currentProjectId, statusLabel);
		// binder.bindInstanceFields(this);
		binder.forField(firstName)
				.withValidator(new StringLengthValidator("Name must be between 2 and 20 characters long", 2, 20))
				.withStatusLabel(statusLabel).bind(Person::getFirstName, Person::setFirstName);
		binder.forField(secondName).bind(Person::getSecondName, Person::setSecondName);
		binder.forField(currentProjectId).bind(Person::getCurrentProjectId, Person::setCurrentProjectId);
		binder.forField(id).withConverter(new StringToIntegerConverter("Wrong value")).bind(Person::getId,
				Person::setId);
		id.setReadOnly(true);

	}

	public void setPerson(Person p) {
		person = p;
		binder.readBean(person);
	}

	public Person writePerson() throws ValidationException {
		binder.writeBean(person);
		return person;
	}
}
