package com.project.questapp.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllLikeResponse {
	
	private Long id;
	private Long userId;
	private Long postId;

}
