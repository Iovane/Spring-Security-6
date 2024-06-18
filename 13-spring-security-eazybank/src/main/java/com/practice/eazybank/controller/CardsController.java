package com.practice.eazybank.controller;

import com.practice.eazybank.model.Cards;
import com.practice.eazybank.model.Customer;
import com.practice.eazybank.repository.CardsRepository;
import com.practice.eazybank.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CardsController {

	@Autowired
	private CardsRepository cardsRepository;

	@Autowired
	CustomerRepository customerRepository;

	@GetMapping("/myCards")
	public List<Cards> getBalanceDetails(@RequestParam String email){

		Customer customer = customerRepository.findByEmail(email);

		if (customer != null) {
			List<Cards> cards = cardsRepository.findByCustomerId(customer.getId());
			if (cards != null) {
				return cards;
			}
		}
		return null;
	}
}
