package com.example.demo.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.validation.*;
import org.springframework.validation.annotation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import com.example.demo.entity.*;
import com.example.demo.service.*;

import jakarta.annotation.*;
import jakarta.servlet.http.*;
import jakarta.validation.*;

@Validated
@Controller
public class EmpController {
	@Autowired
	private EmpService service;
	private Map<String,Object> addInfo;
	
	@PostConstruct
	public void init() {
		addInfo = service.findAddInfo();
	}
	
	// 사원 목록
	@GetMapping({"/", "/emp/list"})
	public ModelAndView list() {
		List<Emp> list = service.list();
		return new ModelAndView("/emp/list").addObject("list", list);
	}
	
	// 사원 상세
	@GetMapping("/emp/read")
	public ModelAndView read(@RequestParam Long empno, HttpSession session) {
		if(session.getAttribute("username")==null)
			return new ModelAndView("redirect:/member/login?required");
		Emp emp = service.read(empno);
		return new ModelAndView("emp/read").addObject("emp", emp);
	}
	
	// 사원 추가
	@GetMapping("/emp/add")
	public ModelAndView add(HttpSession session) {
		if(session.getAttribute("username")==null)
			return new ModelAndView("/redirect:/member/login?required");
		return new ModelAndView("emp/add").addObject("addInfo", addInfo);
	}
	
	@PostMapping("/emp/add")
	public ModelAndView add(@ModelAttribute @Valid Emp emp, BindingResult bindingResult, HttpSession session) {
		if(session.getAttribute("username")==null)
			return new ModelAndView("redirect:/member/login?required");
		service.add(emp);
		return new ModelAndView("redirect:/");
	}
		
	// 사원 삭제
	@PostMapping("/emp/delete")
	public ModelAndView delete(@RequestParam Long empno, HttpSession session) {
		if(session.getAttribute("username")==null)
			return new ModelAndView("redirect:/member/login?required");
		service.delete(empno);
		return new ModelAndView("redirect:/");
	}
}
