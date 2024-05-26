package com.practice.eazybank.controller;

import com.practice.eazybank.config.ProjectSecurityConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class AccountController {

    ProjectSecurityConfig psc = new ProjectSecurityConfig();

    @GetMapping("/myAccount")
    public String getAccountDetails(Principal principal) {
        return "Here are account details from DB " + principal.getName();
    }
}
