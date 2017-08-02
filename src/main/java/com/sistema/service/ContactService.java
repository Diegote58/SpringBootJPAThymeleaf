package com.sistema.service;


import java.util.List;

import com.sistema.entity.Contact;
import com.sistema.model.ContactModel;

public interface ContactService {
	
	public abstract ContactModel addContact(ContactModel contact);
	
	public abstract List<ContactModel> listAllContacts();
	
	public abstract Contact findContactById(int id);
	
	public abstract void removeContact(int id);

	public abstract ContactModel findContactModelById(int id);
}

