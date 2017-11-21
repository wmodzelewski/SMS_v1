package com.cgi.vaadin;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import com.cgi.vaadin.model.Person;
import com.cgi.vaadin.repository.PersonRepository;
import com.vaadin.annotations.Theme;
import com.vaadin.data.ValidationException;
import com.vaadin.icons.VaadinIcons;
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
public class VaadinR2UI extends UI {

	private VerticalLayout mainLayout;

	@Autowired
	private PersonRepository repository;

	Grid<Person> gridPerson;

	private HorizontalLayout hLayout;

	private PersonDetails details;

	@Override
	protected void init(VaadinRequest request) {
		mainLayout = new VerticalLayout();
		setContent(mainLayout);
		Label title = new Label("Vaadin Demo");
		mainLayout.addComponent(title);
		mainLayout.setComponentAlignment(title, Alignment.TOP_CENTER);
		title.addStyleName(ValoTheme.LABEL_H1);
		hLayout = new HorizontalLayout();
		mainLayout.addComponent(hLayout);
		createGrid();
		details = new PersonDetails();
		details.setPerson(new Person());
		VerticalLayout vDetails = new VerticalLayout();
		HorizontalLayout buttons = createButtons();
		vDetails.addComponents(details, buttons);
		hLayout.addComponent(vDetails);

	}

	private HorizontalLayout createButtons() {
		HorizontalLayout but = new HorizontalLayout();

		Button createNew = new Button("New");
		createNew.addClickListener(listener -> {
			details.setPerson(new Person());
		});

		Button save = new Button("Save", VaadinIcons.USER_CHECK);
		save.addClickListener(listener -> {
			Person p;
			try {
				p = details.writePerson();
				// p = repository.save(p);
				details.setPerson(repository.save(p));
				gridPerson.setItems((Collection<Person>) repository.findAll());
				// details.setPerson(new Person());
			} catch (ValidationException e) {
				e.printStackTrace();
				Notification.show("ERROR", "Error during save " + e.getLocalizedMessage(), Type.ERROR_MESSAGE);
			}

		});
		but.addComponents(createNew, save);
		return but;
	}

	private void createGrid() {
		gridPerson = new Grid<>();
		gridPerson.addColumn(Person::getFirstName).setCaption("First Name");
		gridPerson.addColumn(Person::getSecondName).setCaption("Second Name");
		gridPerson.addColumn(Person::getCurrentProjectId).setCaption("Project");
		gridPerson.setItems((Collection<Person>) repository.findAll());

		gridPerson.addSelectionListener(listener -> {
			if (listener.getFirstSelectedItem().isPresent()) {
				details.setPerson(listener.getFirstSelectedItem().get());
			}
		});

		hLayout.addComponent(gridPerson);
	}

}
