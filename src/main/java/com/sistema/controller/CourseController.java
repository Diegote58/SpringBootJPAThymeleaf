package com.sistema.controller;

import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.servlet.ModelAndView;

import com.sistema.entity.Course;
import com.sistema.service.CourseService;

@Controller
@RequestMapping("/course")
public class CourseController {

	private static final String COURSE_VIEW = "listarCursos";

	private static final String COURSE_ADD = "agregarCurso";

	private static final Log LOGGER = LogFactory.getLog(CourseController.class);
		
	@Autowired
	@Qualifier("courseService")
	private CourseService courseService;
	
	@GetMapping("/listar")
	public ModelAndView Listar(){
		LOGGER.info("CALL: " + "ListAllCourses()");
		ModelAndView mav = new ModelAndView(COURSE_VIEW);
		List<Course> cursos = courseService.listAllCourses();
		mav.addObject("cursos", cursos);
		mav.addObject("course",new Course());
		return mav;		
	}
	
	@GetMapping("/agregarCurso")
	public ModelAndView agregarCurso(Model model){
		LOGGER.info("CALL: " + "AgregarCurso()");
		ModelAndView mav = new ModelAndView(COURSE_ADD);
		mav.addObject("course",new Course());
		return mav;
	}
	
	@PostMapping("/save")
	public String addCourse(@ModelAttribute("course") Course curso){
		LOGGER.info("CALL: " + "addCourse()  -- PARAM: " + curso.toString());
		
		try {
			courseService.addCourse(curso);
			LOGGER.info("SUCCESS: ADD CURSO");
		} catch (Exception e) {
			LOGGER.error("ERROR: " + e.getMessage());
		}
		
		return "redirect:/course/listar";
	}
	
	
	
	
}
