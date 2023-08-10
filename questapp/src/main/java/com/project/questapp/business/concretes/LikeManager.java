package com.project.questapp.business.concretes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.project.questapp.business.abstracts.LikeService;
import com.project.questapp.business.requests.CreateLikeRequest;
import com.project.questapp.business.responses.GetAllLikeResponse;
import com.project.questapp.business.responses.GetByIdLikeResponse;
import com.project.questapp.core.utilities.mappers.ModelMapperService;
import com.project.questapp.dataAccess.abstracts.LikeRepository;
import com.project.questapp.entities.Like;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class LikeManager implements LikeService{
	
	private LikeRepository likeRepository;
	private ModelMapperService modelMapperService;
	@Override
	public List<GetAllLikeResponse> getAllLike(Optional<Long> userId, Optional<Long> postId) {
		
		if(userId.isPresent() && postId.isPresent()) {
			List<Like>  likes = this.likeRepository.findByUserIdAndPostId(userId.get(),postId.get());
			List<GetAllLikeResponse> likeResponses = likes.stream().map(like->this.modelMapperService.forResponse()
					.map(like, GetAllLikeResponse.class)).collect(Collectors.toList());
			
			return likeResponses;
		}
		else if(userId.isPresent()) {
			List<Like> likes = this.likeRepository.findByUserId(userId.get());
			List<GetAllLikeResponse> likeResponses = likes.stream().map(like->this.modelMapperService.forResponse()
					.map(like, GetAllLikeResponse.class)).collect(Collectors.toList());
			return likeResponses;
		}
		else if(postId.isPresent()) {
			List<Like> likes = this.likeRepository.findByPostId(postId.get());
			List<GetAllLikeResponse> likeResponses = likes.stream().map(like->this.modelMapperService.forResponse()
					.map(like, GetAllLikeResponse.class)).collect(Collectors.toList());
			return likeResponses;
			
		}
		List<Like> likes = this.likeRepository.findAll();
		List<GetAllLikeResponse> likeResponses = likes.stream().map(like->this.modelMapperService.forResponse()
				.map(like, GetAllLikeResponse.class)).collect(Collectors.toList());
		return likeResponses;
	}
	@Override
	public GetByIdLikeResponse getByIdLike(Long id) {
		Like like = this.likeRepository.findById(id).orElse(null);
		GetByIdLikeResponse likeResponse = this.modelMapperService.forResponse().map(like, GetByIdLikeResponse.class);
		return likeResponse;
	}
	@Override
	public void addLike(CreateLikeRequest createLikeRequest) {
		Like like = this.modelMapperService.forRequest().map(createLikeRequest, Like.class);
		
		this.likeRepository.save(like);
		
	}
	@Override
	public void deleteLike(Long id) {
		this.likeRepository.deleteById(id);
		
	}
	
	

}
