package com.project.questapp.webApi.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.questapp.business.abstracts.PostService;
import com.project.questapp.business.requests.CreatePostRequest;
import com.project.questapp.business.requests.UpdatePostRequest;
import com.project.questapp.business.responses.GetAllPostResponse;
import com.project.questapp.business.responses.GetByIdPostResponse;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/posts")
@AllArgsConstructor
public class PostController {
	
	private PostService postService;
	
	@GetMapping()
	List<GetAllPostResponse> getAllPosts(@RequestParam Optional<Long> userId){
		return postService.getAllPosts(userId);
	}
	@GetMapping("/{id}")
	GetByIdPostResponse getByIdPost(@PathVariable() Long id) {
		return this.postService.getByIdPost(id);
	}
	@PostMapping
	public void addPost(@RequestBody() CreatePostRequest newPost) {
		this.postService.addPost(newPost);
	}
	@PutMapping()
	void updatePost(UpdatePostRequest updatePostRequest) {
		this.postService.updatePost(updatePostRequest);
	}
	@DeleteMapping()
	void deletePost(Long id) {
		this.postService.deletePost(id);
	}

}
