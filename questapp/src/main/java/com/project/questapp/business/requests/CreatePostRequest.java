package com.project.questapp.business.requests;

import java.util.Date;

import lombok.Data;


@Data
public class CreatePostRequest {

	//private Long id;
	

	//@NotNull
	private Long userId;
	private String title;
	private String text;
	Date createDate;
	
	

}
