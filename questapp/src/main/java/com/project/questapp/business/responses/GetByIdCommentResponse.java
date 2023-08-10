package com.project.questapp.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class GetByIdCommentResponse {
	
	private Long userId;
	private Long postId;
	private String text;

}
