package com.project.questapp.webApi.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.questapp.business.abstracts.LikeService;
import com.project.questapp.business.requests.CreateLikeRequest;
import com.project.questapp.business.responses.GetAllLikeResponse;
import com.project.questapp.business.responses.GetByIdLikeResponse;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/likes")
public class LikeController {
	
	private LikeService likeService;
	
	@GetMapping()
	public List<GetAllLikeResponse> getAllLike(@RequestParam Optional<Long> userId ,@RequestParam Optional<Long> postId ){
		return this.likeService.getAllLike(userId, postId);
	}
	@GetMapping("/{id}")
	public GetByIdLikeResponse getByIdLike(@PathVariable Long id) {
		
		return this.likeService.getByIdLike(id);
		
	}
	@PostMapping()
	public void addLike(@RequestBody CreateLikeRequest createLikeRequest) {
		
		this.likeService.addLike(createLikeRequest);
		
	}
	@DeleteMapping("/{id}")
	public void deleteLike(@PathVariable Long id) {
		this.likeService.deleteLike(id);
	}

}
