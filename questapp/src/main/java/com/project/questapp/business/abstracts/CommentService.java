package com.project.questapp.business.abstracts;

import java.util.List;
import java.util.Optional;

import com.project.questapp.business.requests.CreateCommentRequest;
import com.project.questapp.business.requests.UpdateCommentRequest;
import com.project.questapp.business.responses.GetAllCommentResponse;
import com.project.questapp.business.responses.GetByIdCommentResponse;

public interface CommentService {
	
	List<GetAllCommentResponse> getAllCommentsWithParam(Optional<Long> userId,Optional<Long> postId);
	GetByIdCommentResponse getByCommentId(Long id);
	void addComment(CreateCommentRequest commentRequest);
	void updateComment(UpdateCommentRequest commentRequest);
	void deleteComment(Long id);

}
