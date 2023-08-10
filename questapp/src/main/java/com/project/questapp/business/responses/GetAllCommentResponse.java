package com.project.questapp.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllCommentResponse {
	
	private Long id;
	private Long userId;
	//private Long postId;
	private String text;
	private String userName;

}
