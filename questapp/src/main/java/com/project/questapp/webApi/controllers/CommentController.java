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

import com.project.questapp.business.abstracts.CommentService;
import com.project.questapp.business.requests.CreateCommentRequest;
import com.project.questapp.business.requests.UpdateCommentRequest;
import com.project.questapp.business.responses.GetAllCommentResponse;
import com.project.questapp.business.responses.GetByIdCommentResponse;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@RestController
@RequestMapping("/comments")
public class CommentController {
	
	private CommentService commentService;
	
	@GetMapping()
	public List<GetAllCommentResponse> getAllCommentsWithParam(@RequestParam Optional<Long> userId,@RequestParam Optional<Long> postId){
		return this.commentService.getAllCommentsWithParam( userId, postId);
	}
	@GetMapping("/{id}")
	public GetByIdCommentResponse getByCommentId(@PathVariable() Long id) {
		return this.commentService.getByCommentId(id);
	}
	@PostMapping()
	public void addComment(@RequestBody CreateCommentRequest commentRequest) {
		this.commentService.addComment(commentRequest);
		
	}
	@PutMapping()
	public void updateComment( UpdateCommentRequest commentRequest) {
		this.commentService.updateComment(commentRequest);
	}
	@DeleteMapping("/{id}")
	public void deleteComment(@PathVariable Long id) {
		this.commentService.deleteComment(id);
	}

}
