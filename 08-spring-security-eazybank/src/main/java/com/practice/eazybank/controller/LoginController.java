package com.practice.eazybank.controller;

import com.practice.eazybank.model.Customer;
import com.practice.eazybank.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;

@RestController
public class LoginController {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody Customer customer){
		Customer savedCustomer = null;
		ResponseEntity<String> response = null;

		try {
			String hashPwd = passwordEncoder.encode(customer.getPwd());
			customer.setPwd(hashPwd);
			customer.setCreateDt(new Date(System.currentTimeMillis()));
			savedCustomer = customerRepository.save(customer);

			if(savedCustomer.getId() > 0){
				response = ResponseEntity
						.status(HttpStatus.CREATED)
						.body("Customer successfully registered!");
			}
		} catch (Exception ex){
			response = ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An exception has occurred due to: " + ex.getMessage());
		}

		return response;
	}

	@RequestMapping("/user")
	public Customer getUserDetailsAfterLogin(Authentication authentication){
		return customerRepository.findByEmail(authentication.getName());
	}


}
