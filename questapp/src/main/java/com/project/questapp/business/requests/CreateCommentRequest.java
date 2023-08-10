package com.project.questapp.business.requests;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCommentRequest {
	
	private Long userId;
	private Long postId;
	private String text;
	Date createDate;

}
