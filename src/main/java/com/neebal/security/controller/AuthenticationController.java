package com.neebal.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.neebal.model.AuthenticationRequest;
import com.neebal.model.AuthenticationResponse;
import com.neebal.security.service.MyUserDetailsService;
import com.neebal.security.util.JwtUtil;

@RestController
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MyUserDetailsService userDetailService;
	
	@Autowired
	private JwtUtil jwtTokenUtil;
	
	@PostMapping("/user/signin")
	public ResponseEntity<?> createAuthencationTiken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(),authenticationRequest.getPassword()));
			
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect User name and Password",e);
		}
		final UserDetails userDetail=userDetailService.loadUserByUsername(authenticationRequest.getUserName());
		 
		final String jwt=jwtTokenUtil.generateToken(userDetail);
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
}
