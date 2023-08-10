package com.project.questapp.business.abstracts;

import com.project.questapp.entities.RefreshToken;
import com.project.questapp.entities.User;

public interface RefreshTokenService {
	
	
	boolean isRefreshExpired(RefreshToken token);
	String createRefreshToken(User user);
	RefreshToken getByUser(Long userId);
	

}
