package com.sistema.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorController {

	public static final String Error500 = "error/500";
	
	@ExceptionHandler(Exception.class)
	public String InternalServerError(){
		return Error500;
	}
}
