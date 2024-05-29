package com.practice.eazybank.controller;

import com.practice.eazybank.model.Cards;
import com.practice.eazybank.repository.CardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CardsController {

	@Autowired
	private CardsRepository cardsRepository;

	@GetMapping("/myCards")
	public List<Cards> getBalanceDetails(@RequestParam int id){

		List<Cards> cards = cardsRepository.findByCustomerId(id);

		if(cards != null){
			return cards;
		}else {
			return null;
		}
	}
}
