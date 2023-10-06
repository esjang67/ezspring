package com.board.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
public class SampleController {
	
	@GetMapping("/all")
	public void doAll() {
		log.info("모든 사용자 접속 가능");
	}
	
	@GetMapping("/member")
	public void doMember() {
		log.info("로그인 사용자만 접근 가능");
	}
	
	@GetMapping("/admin")
	public void doAdmin() {
		log.info("관리자만 접근 가능");
	}
	
	@GetMapping("/accessError")
	public void accessError(Authentication auth, Model model) {
		model.addAttribute("msg", auth.getName() + " 접근 권한 없음");
	}
	
	@GetMapping("/customLogin")
	public void loginPage(Model model, String error, String logout) {
		// login fail
		if(error!=null) {
			model.addAttribute("error","login fail");
		} else if(logout!=null) {
			model.addAttribute("logout","logout OK");
		}
	}
	
	@GetMapping("/customLogout")
	public void logout() {
		
	}
	
}
