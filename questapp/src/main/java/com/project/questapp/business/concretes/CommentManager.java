package com.project.questapp.business.concretes;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.project.questapp.business.abstracts.CommentService;
import com.project.questapp.business.requests.CreateCommentRequest;
import com.project.questapp.business.requests.UpdateCommentRequest;
import com.project.questapp.business.responses.GetAllCommentResponse;
import com.project.questapp.business.responses.GetByIdCommentResponse;
import com.project.questapp.core.utilities.mappers.ModelMapperService;
import com.project.questapp.dataAccess.abstracts.CommentRepository;
import com.project.questapp.entities.Comment;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service()
public class CommentManager implements CommentService {
	
	private CommentRepository commentRepository;
	private ModelMapperService modelMapperService;

	@Override
	public List<GetAllCommentResponse> getAllCommentsWithParam(Optional<Long> userId, Optional<Long> postId) {

		if(userId.isPresent() && postId.isPresent()) {
			List<Comment> comments = this.commentRepository.findByUserIdAndPostId(userId.get(), postId.get());
			List<GetAllCommentResponse> commentResponses = comments.stream().map(comment->this.modelMapperService.forResponse()
					.map(comment, GetAllCommentResponse.class)).collect(Collectors.toList());
			return commentResponses;
		}
		else if(userId.isPresent()) {
			List<Comment> comments = this.commentRepository.findByUserId(userId.get());
			List<GetAllCommentResponse> commentResponses = comments.stream().map(comment->this.modelMapperService.forResponse()
					.map(comment, GetAllCommentResponse.class)).collect(Collectors.toList());
			
			return commentResponses;
			
		}
		else if(postId.isPresent()){
			List<Comment> comments = this.commentRepository.findByPostId(postId.get());
			List<GetAllCommentResponse> commentResponses = comments.stream().map(comment->this.modelMapperService.forResponse()
					.map(comment, GetAllCommentResponse.class)).collect(Collectors.toList());
			return commentResponses;
		}
		List<Comment> comments = this.commentRepository.findAll();
		List<GetAllCommentResponse> commentResponses = comments.stream().map(comment->this.modelMapperService.forResponse()
				.map(comment, GetAllCommentResponse.class)).collect(Collectors.toList());
		return commentResponses;
	}

	@Override
	public GetByIdCommentResponse getByCommentId(Long id) {
		Comment comment = this.commentRepository.findById(id).orElse(null);
		GetByIdCommentResponse commentResponse = this.modelMapperService.forResponse().map(comment, GetByIdCommentResponse.class);
		return commentResponse;
	}

	@Override
	public void addComment(CreateCommentRequest commentRequest) {
		Comment comment = this.modelMapperService.forRequest().map(commentRequest, Comment.class);
		comment.setCreateDate(new Date());
		
		this.commentRepository.save(comment);
		
	}

	@Override
	public void updateComment(UpdateCommentRequest commentRequest) {
		Comment comment = this.modelMapperService.forRequest().map(commentRequest, Comment.class);
		
		this.commentRepository.save(comment);
		
	}

	@Override
	public void deleteComment(Long id) {
		this.commentRepository.deleteById(id);
		
	}

}
