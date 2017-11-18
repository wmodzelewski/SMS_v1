package com.wmodzelewski.ui;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Theme;
import com.vaadin.data.Binder;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.wmodzelewski.model.Person;
import com.wmodzelewski.repository.PersonRepository;

@Theme("t1_vaadintheme")
@SpringUI
public class VaadinStarter extends UI {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6867013362933353875L;
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

		Binder<Person> binder = new Binder<>(Person.class);

		FormLayout fields = new FormLayout();

		TextField id = new TextField("id");
		TextField firstName = new TextField("firstName");
		TextField secondName = new TextField("secondName");
		TextField currentProjectId = new TextField("currentProjectId");
		fields.addComponents(id, firstName, secondName, currentProjectId);

		binder.forField(id).bind(Person::getId, Person::setId);
		binder.forField(firstName).bind(Person::getFirstName, Person::setFirstName);
		binder.forField(secondName).bind(Person::getSecondName, Person::setSecondName);
		binder.forField(currentProjectId).bind(Person::getCurrentProjectId, Person::setCurrentProjectId);

		hl.addComponent(fields);

		personGrid.addSelectionListener(listener -> {

			Optional<Person> p = listener.getFirstSelectedItem();
			p.ifPresent(consumer -> {
				binder.setBean(consumer);
			});

		});
	}

}
