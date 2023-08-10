package com.project.questapp.business.abstracts;

import java.util.List;
import java.util.Optional;

import com.project.questapp.business.requests.CreatePostRequest;
import com.project.questapp.business.requests.UpdatePostRequest;
import com.project.questapp.business.responses.GetAllPostResponse;
import com.project.questapp.business.responses.GetByIdPostResponse;

public interface PostService {
	
	List<GetAllPostResponse> getAllPosts(Optional<Long> userId);
	GetByIdPostResponse getByIdPost(Long id);
	void addPost(CreatePostRequest newPost);
	void updatePost(UpdatePostRequest updatePostRequest);
	void deletePost(Long id);
	public GetByIdPostResponse getByIdPostWithLikes(Long id);

}
