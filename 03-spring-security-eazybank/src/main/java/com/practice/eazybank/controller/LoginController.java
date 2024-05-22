package com.practice.eazybank.controller;

import com.practice.eazybank.model.Customer;
import com.practice.eazybank.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

	@Autowired
	CustomerRepository costumerRepository;

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody Customer customer){
		Customer savedCustomer;
		ResponseEntity<String> response = null;

		try {
			savedCustomer = costumerRepository.save(customer);
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

}
