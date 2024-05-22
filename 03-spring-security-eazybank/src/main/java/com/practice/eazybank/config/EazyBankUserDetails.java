package com.practice.eazybank.config;

import com.practice.eazybank.model.Customer;
import com.practice.eazybank.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EazyBankUserDetails implements UserDetailsService {

	@Autowired
	CustomerRepository customerRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		String customerEmail, password;
		List<GrantedAuthority> authorities;
		Customer customer = customerRepository.findByEmail(email);

		if (customer == null){
			throw new UsernameNotFoundException("User details not found for the user: " + email);

		} else {
			customerEmail = customer.getEmail();
			password = customer.getPwd();
			authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority(customer.getRole()));
		}
		return new User(customerEmail, password, authorities);
	}
}
