package com.practice.eazybank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class ProjectSecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((requests) -> requests
						.requestMatchers("/myAccount", "myBalance", "myCards", "myLoans").authenticated()
						.requestMatchers("/notices", "/contact", "/register").permitAll())
				.formLogin(Customizer.withDefaults())
				.httpBasic(Customizer.withDefaults());

		http.csrf().disable();

		return http.build();
	}

//	@Bean
//	public InMemoryUserDetailsManager userDetailsManager() {
//
//        /* Approach 1 where we use withDefaultPasswordEncoder() method
//        while creating the user details
//        */
//
//        /*
//        UserDetails admin = User.withDefaultPasswordEncoder()
//                .username("admin")
//                .password("admin")
//                .authorities("ADMIN")
//                .build();
//
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("user")
//                .authorities("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(admin, user);
//        */
//
//
//        /* Approach 2 where we use NoOpPasswordEncoder.getInstance() method
//        while creating the user details */
//
//		UserDetails admin = User.withUsername("admin")
//				.password("admin")
//				.authorities("ADMIN")
//				.build();
//
//		UserDetails user = User.withUsername("user")
//				.password("user")
//				.authorities("USER")
//				.build();
//
//		return new InMemoryUserDetailsManager(admin, user);
//	}

/*	@Bean
	public UserDetailsService userDetailsService(DataSource dataSource) {
		return new JdbcUserDetailsManager(dataSource);
	}*/

	/*
	 * NoOpPasswordEncoder is not recommended for production usage.
	 * Use only for non-prod.
	 *
	 * @return PasswordEncoder
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

}

