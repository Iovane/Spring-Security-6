package com.practice.eazybank.controller;

import com.practice.eazybank.model.Customer;
import com.practice.eazybank.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

	@Autowired
	private CustomerRepository customerRepository;

	@RequestMapping("/user")
	public Customer getUserDetailsAfterLogin(Authentication authentication){
		return customerRepository.findByEmail(authentication.getName());
	}


}
