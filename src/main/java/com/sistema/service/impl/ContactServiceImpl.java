package com.sistema.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sistema.component.ContactConverter;
import com.sistema.entity.Contact;
import com.sistema.model.ContactModel;
import com.sistema.repository.ContactRepository;
import com.sistema.service.ContactService;

@Service("contactService")
public class ContactServiceImpl implements ContactService{

	@Autowired
	@Qualifier("contactRepository")
	private ContactRepository contactRepository;
	
	@Autowired
	@Qualifier("contactConverter")
	private ContactConverter contactConverter;
	
	@Override
	public ContactModel addContact(ContactModel model){
		Contact contact = contactRepository.save(contactConverter.ContactModelToContact(model));
		return contactConverter.ContactToContactModel(contact);
	}

	@Override
	public List<ContactModel> listAllContacts() {
		List<Contact> contacts = contactRepository.findAll();
		List<ContactModel> contactModels=new ArrayList<ContactModel>();
		
		for (Contact contact : contacts) {
			contactModels.add(contactConverter.ContactToContactModel(contact));
		}
		
		return contactModels;
	}

	@Override
	public Contact findContactById(int id) {
		return contactRepository.findById(id);
	}
	
	@Override
	public ContactModel findContactModelById(int id) {
		return contactConverter.ContactToContactModel(contactRepository.findById(id));
	}

	@Override
	public void removeContact(int id) {
		Contact contact = contactRepository.findById(id);
		if (contact != null) {
			contactRepository.delete(contact);
		}

	}

}
