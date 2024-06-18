package com.practice.eazybank.controller;

import com.practice.eazybank.model.AccountTransactions;
import com.practice.eazybank.model.Accounts;
import com.practice.eazybank.model.Customer;
import com.practice.eazybank.repository.AccountTransactionsRepository;
import com.practice.eazybank.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BalanceController {

    @Autowired
    private AccountTransactionsRepository accountTransactionsRepository;

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("/myBalance")
    public List<AccountTransactions> getBalanceDetails(@RequestParam String email){

        Customer customer = customerRepository.findByEmail(email);

        if (customer != null) {
            List<AccountTransactions> accountTransactions = accountTransactionsRepository.findByCustomerIdOrderByTransactionDateDesc(customer.getId());
            if (accountTransactions != null) {
                return accountTransactions;
            }
        }
        return null;
    }
}
