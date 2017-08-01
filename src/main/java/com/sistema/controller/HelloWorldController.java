package com.sistema.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sistema.component.ExampleComponent;
import com.sistema.model.Person;
import com.sistema.service.ExampleService;

@Controller
@RequestMapping("/")
public class HelloWorldController {

	public static final String EXAMPLE_VIEW = "listar_person";
	public static final String EXAMPLE_VIEW2 = "get_person_name";

	@Autowired
	@Qualifier("exampleComponent")
	private ExampleComponent component;
	
	@Autowired
	@Qualifier("exampleService")
	private ExampleService exampleService;
	
	@GetMapping("helloworld")
	public String HelloWorld() {
		component.sayHello();
		return "helloworld";
	}
	
	@GetMapping("template")
	public String Template() {
		component.sayHello();
		return "layout/template";
	}

	//Primera forma
	@GetMapping("exampleString")
	public String Example(Model model) {
		model.addAttribute("people", exampleService.getPeopleList());
		return EXAMPLE_VIEW;
	}

	//Segunda forma
	@GetMapping("exampleModel")
	public ModelAndView Example2() {
		ModelAndView mav = new ModelAndView(EXAMPLE_VIEW);
		mav.addObject("people", exampleService.getPeopleList());
		return mav;
	}

	
	
	
		// Enviar Parametros por GET example: /getname?nm=pepe
		@GetMapping("request1")
		public ModelAndView getName(@RequestParam(name="nm",required=false,defaultValue="Null") String name) {
			ModelAndView mav = new ModelAndView(EXAMPLE_VIEW2);
			mav.addObject("name", name);
			return mav;
		}
		
		// Enviar Parametros por GET example: /getname?nm=pepe
				@GetMapping("request2/{nm}")
				public ModelAndView getName2(@PathVariable(name="nm") String name) {
					ModelAndView mav = new ModelAndView(EXAMPLE_VIEW2);
					mav.addObject("name", name);
					return mav;
				}
}
