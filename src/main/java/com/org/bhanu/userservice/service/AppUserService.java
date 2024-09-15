package com.org.bhanu.userservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.org.bhanu.userservice.entity.AppUser;
import com.org.bhanu.userservice.enums.Role;
import com.org.bhanu.userservice.repository.AppUserRepository;

@Service
public class AppUserService {
	
	@Autowired
	private AppUserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtService jwtService;
	
	
	public String registerUser(AppUser user) {
		try {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			user.setFsId(generateFinShiharaiId(user.getEmail()));
			user.setRole(Role.USER);
			userRepository.save(user);
			return "User saved succesfully";
		}catch (Exception e) {
			throw new RuntimeException("Got error saving user");
		}
	}
	
	
	private String generateFinShiharaiId(String email) {
		String strs[] = email.split("@");
		String finShiharaiId = strs[0]+"@shiharai";
		return finShiharaiId;
	}
	
	
	
	
	public String generateToken(String email) {
		return jwtService.generateToken(email);
	}
	
	
	public void validateToken(String token) {
		jwtService.validateToken(token);
	}
	
	
	
	public AppUser getUser(Long userId) {
		return userRepository.findById(userId).get();
	}
	
	
	public List<AppUser> getAllUsers(){
		return userRepository.findAll();
	}

}
