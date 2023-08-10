package com.project.questapp.business.abstracts;

import java.util.List;
import java.util.Optional;

import com.project.questapp.business.requests.CreateLikeRequest;
import com.project.questapp.business.responses.GetAllLikeResponse;
import com.project.questapp.business.responses.GetByIdLikeResponse;

public interface LikeService {
	
	List<GetAllLikeResponse> getAllLike(Optional<Long> userId ,Optional<Long> postId );
	GetByIdLikeResponse  getByIdLike(Long id);
	void addLike(CreateLikeRequest createLikeRequest) ;
	void deleteLike(Long id);
	

}
