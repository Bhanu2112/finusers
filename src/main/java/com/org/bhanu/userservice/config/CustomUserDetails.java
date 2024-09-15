package com.org.bhanu.userservice.config;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.org.bhanu.userservice.entity.AppUser;

public class CustomUserDetails implements UserDetails {
	
	private String username;
	private String password;
	
	public CustomUserDetails(AppUser appUser) {
		this.username = appUser.getEmail();
		this.password = appUser.getPassword();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

}
