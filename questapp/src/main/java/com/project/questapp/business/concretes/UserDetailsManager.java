package com.project.questapp.business.concretes;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.questapp.business.abstracts.UserDetailsServiceImp;
import com.project.questapp.dataAccess.abstracts.UserRepository;
import com.project.questapp.entities.User;
import com.project.questapp.security.JwtUserDetails;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserDetailsManager implements UserDetailsServiceImp{

	private UserRepository userRepository;
	
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = this.userRepository.findByUserName(username);
		return JwtUserDetails.create(user);
	}
	
	public UserDetails loadUserId(Long id) {
		User user = this.userRepository.findById(id).get();
		
		return JwtUserDetails.create(user);
	}

}
