package com.practice.eazybank.controller;

import com.practice.eazybank.model.Customer;
import com.practice.eazybank.model.Loans;
import com.practice.eazybank.repository.CustomerRepository;
import com.practice.eazybank.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoansController {

	@Autowired
	private LoanRepository loanRepository;

	@Autowired
	CustomerRepository customerRepository;

	@GetMapping("/myLoans")
	@PreAuthorize("hasRole('USER')")
	public List<Loans> getBalanceDetails(@RequestParam String email) {

		Customer customer = customerRepository.findByEmail(email);

		if (customer != null) {
			List<Loans> loans = loanRepository.findByCustomerIdOrderByStartDtDesc(customer.getId());
			if (loans != null) {
				return loans;
			}
		}
		return null;
	}
}
