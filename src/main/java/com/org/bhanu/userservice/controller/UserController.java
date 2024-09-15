package com.org.bhanu.userservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.bhanu.userservice.entity.AppUser;
import com.org.bhanu.userservice.service.AppUserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private AppUserService userService;
	
	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome to userservice";
	}
	
	
	@GetMapping("/finduser/{userId}")
	public AppUser findUser(@PathVariable Long userId) {
		return userService.getUser(userId);
	}
	
	@GetMapping("/allusers")
	public List<AppUser> findAllUsers(){
		return userService.getAllUsers();
	}

}
