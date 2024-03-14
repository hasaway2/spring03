package com.example.demo.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import com.example.demo.dto.*;
import com.example.demo.service.*;

import jakarta.servlet.http.*;

@Controller
public class LoginController {
	@Autowired
	private LoginService service; 
	
	@GetMapping("/member/login")
	public void login() {
	}
	
	@PostMapping("/member/login")
	public ModelAndView login(@ModelAttribute LoginDto loginDto, HttpSession session) {
		Boolean result = service.login(loginDto);
		if(result) {
			session.setAttribute("username", loginDto.getUsername());
			return new ModelAndView("redirect:/");
		}
		return new ModelAndView("redirect:/member/login?error");
	}
	
	@PostMapping("/member/logout")
	public ModelAndView login(HttpSession session) {
		session.invalidate();
		return new ModelAndView("redirect:/");
	}
}
