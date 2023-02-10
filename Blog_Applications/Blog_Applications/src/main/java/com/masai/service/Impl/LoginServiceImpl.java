package com.masai.service.Impl;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;

import com.masai.dto.LoginDto;
import com.masai.repository.RoleRepository;
import com.masai.repository.UserRepository;
import com.masai.service.LoginService;

public class LoginServiceImpl implements LoginService{
	
	@Autowired
	private UserRepository ur;
	
	@Autowired
	private RoleRepository rp;

	@Override
	public String logIntoAccount(LoginDto dto) throws LoginException {
		if(dto.getUsernameOrEmail().equalsIgnoreCase("customer")) {
			
		}
		
		return null;
	}

	@Override
	public String logOutFromAccount(String key) throws LoginException {
		// TODO Auto-generated method stub
		return null;
	}

}
