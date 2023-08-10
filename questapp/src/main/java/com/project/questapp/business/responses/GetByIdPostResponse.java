package com.project.questapp.business.responses;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetByIdPostResponse {
	
	Long id;
	Long userId;
	String userName;
	String title;
	String text;
	List<GetAllLikeResponse> postLikes;
}
