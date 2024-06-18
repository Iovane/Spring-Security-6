package com.practice.eazybank.controller;

import com.practice.eazybank.model.Accounts;
import com.practice.eazybank.model.Customer;
import com.practice.eazybank.repository.AccountsRepository;
import com.practice.eazybank.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountController {

	@Autowired
	private AccountsRepository accountsRepository;

	@Autowired
	CustomerRepository customerRepository;


	@GetMapping("/myAccount")
	public Accounts getAccountDetails(@RequestParam String email) {

		Customer customer = customerRepository.findByEmail(email);

		if (customer != null) {
			Accounts accounts = accountsRepository.findByCustomerId(customer.getId());
			if (accounts != null) {
				return accounts;
			}
		}
		return null;
	}
}
