package com.test.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

//예외발생을 위한 클래스
@ControllerAdvice		// 모든 컨트롤러들의 예외처리
public class CommonExceptionAdvice {
	
	@ExceptionHandler(Exception.class)
	public String exception(Exception e, Model model) {
		System.out.println("예외발생 : " + e.getMessage());
		model.addAttribute("exception", e);
		
		return "errorPage";
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND) //404
	public String pageNotFound(NoHandlerFoundException e) {
		return "pageNotFound";
	}
	
}
