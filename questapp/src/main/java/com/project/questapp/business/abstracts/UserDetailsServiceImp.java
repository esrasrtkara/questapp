package com.project.questapp.business.abstracts;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserDetailsServiceImp extends UserDetailsService{
	
	public UserDetails loadUserId(Long id);

}
