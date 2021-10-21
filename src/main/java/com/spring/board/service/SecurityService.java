package com.spring.board.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {
	private AuthenticationManager authenticationManager;
	private UserDetailsService userDetailsService;
	
	public SecurityService(AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
		this.authenticationManager = authenticationManager;
		this.userDetailsService = userDetailsService;
	}
	
	public boolean login(String username, String password) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		
		UsernamePasswordAuthenticationToken token = 
			new UsernamePasswordAuthenticationToken(authenticationManager, password, userDetails.getAuthorities());
		
		authenticationManager.authenticate(token);
		boolean result = token.isAuthenticated();
		
		if (result) {
			SecurityContextHolder.getContext().setAuthentication(token);
		}
		
		return result;
	}
}
