package com.org.bhanu.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.org.bhanu.userservice.dto.AuthRequest;
import com.org.bhanu.userservice.entity.AppUser;
import com.org.bhanu.userservice.exception.UserNotFoundException;
import com.org.bhanu.userservice.service.AppUserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	
	@Autowired
	private AppUserService appUserService;
	
	
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome to auth controller";
	}
	
	
	@PostMapping("/register")
	public String registerUser( @RequestBody AppUser user) {
		return appUserService.registerUser(user);
	}
	
	@PostMapping("/token")
	public String getToken(@RequestBody AuthRequest authRequest) throws UserNotFoundException {
		
		Authentication authentication =  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
		
		if(authentication.isAuthenticated()) {
			return appUserService.generateToken(authRequest.getEmail());
		}else {
			throw new UserNotFoundException("Password or username might wront");
		}
		
		
		
	}
	
	
	@GetMapping("/validate")
	public String validateToken(@RequestParam String token) {
		appUserService.validateToken(token);
		
		return "Token is valid";
	}

}
