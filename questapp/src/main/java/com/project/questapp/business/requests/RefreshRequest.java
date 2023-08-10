package com.project.questapp.business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data

public class RefreshRequest {
	
	private Long userId;
	private String refreshToken;

}
