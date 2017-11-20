package com.cgi.vaadin.ui;

import com.cgi.vaadin.model.Person;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;

public class PersonDetails extends FormLayout {

	TextField id = new TextField("id");
	TextField firstName = new TextField("firstName");
	TextField secondName = new TextField("secondName");
	TextField currentProjectId = new TextField("currentProjectId");
	Label status = new Label();
	private Binder<Person> binder = new Binder<>(Person.class);
	Person person = null;

	public PersonDetails() {

		binder.forField(id).withConverter(new StringToIntegerConverter("Need to be integer")).bind("id");

		// binder.forField(firstName).withValidator(name -> name.length() >= 2, "First
		// Name too short")
		// .withStatusLabel(status).bind(Person::getFirstName, Person::setFirstName);

		binder.forField(firstName).withValidator(name -> name.length() >= 2, "First Name too short")
				.bind(Person::getFirstName, Person::setFirstName);

		binder.forField(secondName).bind("secondName");
		binder.forField(currentProjectId).bind("currentProjectId");

		id.setReadOnly(true);

		// binder.bindInstanceFields(this);
		addComponents(id, firstName, secondName, currentProjectId, status);
		setDefaultComponentAlignment(Alignment.TOP_CENTER);
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
