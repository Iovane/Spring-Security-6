package com.practice.eazybank.controller;

import com.practice.eazybank.model.Loans;
import com.practice.eazybank.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoansController {

    @Autowired
    private LoanRepository loanRepository;

    @GetMapping("/myLoans")
    public List<Loans> getBalanceDetails(@RequestParam int id){

        List<Loans> loans = loanRepository.findByCustomerIdOrderByStartDtDesc(id);

        if (loans != null){
            return loans;
        } else return null;
    }
}