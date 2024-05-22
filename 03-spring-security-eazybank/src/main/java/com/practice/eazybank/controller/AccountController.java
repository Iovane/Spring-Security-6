package com.practice.eazybank.controller;

import com.practice.eazybank.config.ProjectSecurityConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    ProjectSecurityConfig psc = new ProjectSecurityConfig();

    @GetMapping("/myAccount")
    public String getAccountDetails() {
        return "Here are account details from DB";
    }
}
