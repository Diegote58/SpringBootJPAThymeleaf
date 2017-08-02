package com.sistema.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sistema.constant.ViewConstant;
import com.sistema.model.ContactModel;
import com.sistema.service.ContactService;


@Controller
@RequestMapping("/contacts")
public class ContactController {

	private static final Log LOGGER = LogFactory.getLog(ContactController.class);

	@Autowired
	@Qualifier("contactService")
	private ContactService contactService;

	@GetMapping("/cancel")
	public String cancel() {
		return "redirect:/contacts/showContacts";
	}

	@GetMapping("/contactForm")
	public String showContactForm(Model model,@RequestParam(name="id",defaultValue="0",required=false) int id) {
		
		ContactModel contact = new ContactModel();
		if (id != 0) {
			contact = contactService.findContactModelById(id);
		}
		model.addAttribute("contactModel", contact);
		
		return ViewConstant.CONTACT_FORM;
	}

	@PostMapping("/addContact")
	public String addContact(Model model, @ModelAttribute(name = "contactModel") ContactModel contact) {

		LOGGER.info("METHOD: addContact -- PARAMS: " + contact.toString());
		if (contactService.addContact(contact) != null) {
			model.addAttribute("result", 1);
		} else {
			model.addAttribute("result", 0);
		}
		return "redirect:/contacts/showContacts";

	}
	
	@GetMapping("/showContacts")
	public ModelAndView showContacts(){
		ModelAndView mav = new ModelAndView(ViewConstant.CONTACTS);
		mav.addObject("contacts",contactService.listAllContacts());		
		return mav;
	}
	
	@GetMapping("/removeContact")
	public ModelAndView removeContact(@RequestParam(name="id",required=true) int id ){
		contactService.removeContact(id);
		return showContacts();
	}
}
