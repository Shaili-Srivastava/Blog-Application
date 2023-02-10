package com.masai.service;

import javax.security.auth.login.LoginException;

import com.masai.dto.LoginDto;

public interface LoginService {
	
	public String logIntoAccount(LoginDto dto)throws LoginException;

	public String logOutFromAccount(String key)throws LoginException;

}
