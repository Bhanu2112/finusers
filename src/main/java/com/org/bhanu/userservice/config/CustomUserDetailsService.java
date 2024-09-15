package com.org.bhanu.userservice.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.org.bhanu.userservice.entity.AppUser;
import com.org.bhanu.userservice.exception.UserNotFoundException;
import com.org.bhanu.userservice.repository.AppUserRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private AppUserRepository appUserRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		
	 Optional<AppUser> appUser = appUserRepository.findByEmail(username);
		return appUser.map(CustomUserDetails::new).orElseThrow(() -> new UserNotFoundException("user not found"));
	}

}
