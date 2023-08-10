package com.project.questapp.business.concretes;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.project.questapp.business.abstracts.LikeService;
import com.project.questapp.business.abstracts.PostService;
import com.project.questapp.business.requests.CreatePostRequest;
import com.project.questapp.business.requests.UpdatePostRequest;
import com.project.questapp.business.responses.GetAllLikeResponse;
import com.project.questapp.business.responses.GetAllPostResponse;
import com.project.questapp.business.responses.GetByIdPostResponse;
import com.project.questapp.core.utilities.mappers.ModelMapperService;
import com.project.questapp.dataAccess.abstracts.PostRepository;
import com.project.questapp.entities.Post;

import lombok.AllArgsConstructor;
import lombok.Data;
@Service
@AllArgsConstructor
@Data
public class PostManager implements PostService{
	
	private PostRepository postRepository;
	private ModelMapperService modelMapperService;
	private LikeService likeService;
	
	

	@Override
	public List<GetAllPostResponse> getAllPosts(Optional<Long> userId) {
		List<Post> posts;
		List<GetAllPostResponse> postResponses;
		if(userId.isPresent()) {
			posts = this.postRepository.findByUserId(userId.get());
			postResponses = posts.stream().map(post->this.modelMapperService.forResponse()
					.map(post,GetAllPostResponse.class)).collect(Collectors.toList());
			return postResponses;
		}
		posts = this.postRepository.findAll();
		postResponses = posts.stream().map(post->  {
		List<GetAllLikeResponse> likes = this.likeService.getAllLike(Optional.ofNullable(null), Optional.of(post.getId()));
		return this.modelMapperService.forResponse()
				.map(post, GetAllPostResponse.class);}).collect(Collectors.toList());
		
		return postResponses;
		
	}

	@Override
	public GetByIdPostResponse getByIdPost(Long id) {
		
		Post post = this.postRepository.findById(id).orElse(null);
		GetByIdPostResponse postResponse = this.modelMapperService.forResponse().map(post, GetByIdPostResponse.class);
		return   postResponse;
	}
	
	@Override
	public GetByIdPostResponse getByIdPostWithLikes(Long id) {
		
		Post post = this.postRepository.findById(id).orElse(null);
		List<GetAllLikeResponse> likes = this.likeService.getAllLike(Optional.ofNullable(null), Optional.of(id));
		GetByIdPostResponse postResponse = this.modelMapperService.forResponse().map(post, GetByIdPostResponse.class);
		postResponse = this.modelMapperService.forResponse().map(likes,GetByIdPostResponse.class );
		return   postResponse;
	}

	


	@Override
	public void updatePost(UpdatePostRequest updatePostRequest) {
		Post post = this.modelMapperService.forRequest().map(updatePostRequest, Post.class);
		
		
		this.postRepository.save(post);
		
	}

	@Override
	public void deletePost(Long id) {
		this.postRepository.deleteById(id);
		
	}

	@Override
	public void addPost(CreatePostRequest newPost) {
	
		Post post = this.modelMapperService.forRequest().map(newPost, Post.class);
		post.setCreateDate(new Date());
		
	
		this.postRepository.save(post);
		
	}
	

}
