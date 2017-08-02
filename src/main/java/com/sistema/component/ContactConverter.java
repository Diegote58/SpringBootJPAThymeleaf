package com.sistema.component;

import org.springframework.stereotype.Component;

import com.sistema.entity.Contact;
import com.sistema.model.ContactModel;

@Component("contactConverter")
public class ContactConverter {

	public Contact ContactModelToContact(ContactModel model) {
		Contact contact = new Contact(model.getId(), model.getFirstname(), model.getLastname(), model.getTelephone(),
				model.getCity());
		return contact;
	}

	public ContactModel ContactToContactModel(Contact contact) {
		ContactModel model = new ContactModel(contact.getId(), contact.getFirstname(), contact.getLastname(),
				contact.getTelephone(), contact.getCity());
		return model;
	}
}
