package com.practice.eazybank.filter;

import jakarta.servlet.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;
import java.util.logging.Logger;

public class AuthoritiesLoginAfterFilter implements Filter {

	private final Logger LOG = Logger.getLogger(this.getClass().getName());

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			LOG.info("User " + authentication.getName() + " logged in and has authorities " +
					authentication.getAuthorities());
		}

		chain.doFilter(request, response);
	}
}
