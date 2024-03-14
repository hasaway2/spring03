package com.example.demo.service;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.example.demo.dto.*;

@Service
public class LoginService {
	@Value("${sample3.admin.username}")
	private String username;
	@Value("${sample3.admin.password}")
	private String password;
	
	public Boolean login(LoginDto loginDto) {
		return loginDto.getUsername().equals(username) && loginDto.getPassword().equals(password);
	}

}
