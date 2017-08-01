package com.sistema.controller;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.sistema.model.Person;

@Controller
@RequestMapping("/post")
public class PostController {

	private static final Log LOGGER = LogFactory.getLog(PostController.class);
	
	
	public static final String FORM1 = "formulario";
	public static final String FORM_RESULT = "result_form";
	
	/* #1
	@GetMapping("/")
	public String redirect(){
		return "redirect:/post/formulario";
	}*/
	
	//#2
	@GetMapping("/")
	public RedirectView redirect(){
		
		return new RedirectView(FORM1);
	}
	
	@GetMapping("/formulario")
	public String showForm(Model model){
		model.addAttribute("person",new Person());
		
		LOGGER.info("INFO TRACE");
		LOGGER.warn("WARNING TRACE");
		LOGGER.error("ERROR TRACE");
		LOGGER.debug("DEBUG");
		return FORM1;
	}

	@PostMapping("/addperson")
	public ModelAndView addPerson(@Valid @ModelAttribute("person") Person person, BindingResult bindR){
		ModelAndView mav = new ModelAndView();
		if (bindR.hasErrors()) {
			LOGGER.info("METHOD: 'addperson' -- ERRORS: Validación de Persona");
			mav.setViewName(FORM1);
			return mav;
		} else {
			mav.setViewName(FORM_RESULT);
			mav.addObject("person", person);
			LOGGER.info("METHOD: 'addperson' -- PARAMS: '" + person + "'");
			LOGGER.info("TEMPLATE: '"+ FORM_RESULT + "' -- DATA: '" + person + "'");
			return mav;
		}
	}
}
