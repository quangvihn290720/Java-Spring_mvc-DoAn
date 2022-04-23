package com.khoaluantotnghiep.controller.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ExceptionController {
	@ExceptionHandler(value = { NoHandlerFoundException.class })
	public String ExceptionHandler(Exception exception) {
		return "error404";
	}
}
