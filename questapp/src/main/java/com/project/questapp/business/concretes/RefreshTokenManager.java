package com.project.questapp.business.concretes;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.project.questapp.business.abstracts.RefreshTokenService;
import com.project.questapp.dataAccess.abstracts.RefreshTokenRepository;
import com.project.questapp.entities.RefreshToken;
import com.project.questapp.entities.User;






@Service()

public class RefreshTokenManager implements RefreshTokenService {
	
	@Value("${refresh.token.expires.in}")
	 Long expireSecond;
	
	private RefreshTokenRepository refreshTokenRepository;
	
	public RefreshTokenManager(RefreshTokenRepository refreshTokenRepository) {
		this.refreshTokenRepository = refreshTokenRepository;
	}

	@Override
	public boolean isRefreshExpired(RefreshToken token) {
		
		return token.getExpiryDate().before(new Date());
	}



	@Override
	public String createRefreshToken(User user) {
		RefreshToken token = new RefreshToken();
		token.setUser(user);
		token.setToken(UUID.randomUUID().toString());
		token.setExpiryDate(Date.from(Instant.now().plusSeconds(expireSecond)));
		refreshTokenRepository.save(token);
		return token.getToken();
	}



	@Override
	public RefreshToken getByUser(Long userId) {
		
		return refreshTokenRepository.findByUserId(userId);
	}

}
