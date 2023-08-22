package com.dairy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dairy.model.LogIn;
import com.dairy.repository.LogInRepo;

@Service
public class LogInService {
	
	@Autowired LogInRepo logInRepo ;

	public LogIn fingByUserNameAndPassword(String username, String password) {
		// TODO Auto-generated method stub
		return fingByUserNameAndPassword(username, password);
	}

	public LogIn findByUsername(String username) {
		// TODO Auto-generated method stub
		return logInRepo.findByUsername(username);
	}

	public LogIn findByUserNameAndPassword(String username, String password) {
		// TODO Auto-generated method stub
		return findByUserNameAndPassword(username,password);
	}

}
