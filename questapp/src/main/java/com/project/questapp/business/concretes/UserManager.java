package com.project.questapp.business.concretes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.questapp.business.abstracts.UserService;
import com.project.questapp.business.requests.CreateUserRequest;
import com.project.questapp.business.requests.UpdateUserRequest;
import com.project.questapp.business.responses.GetAllUserResponse;
import com.project.questapp.business.responses.GetByIdUserResponse;
import com.project.questapp.core.utilities.mappers.ModelMapperService;
import com.project.questapp.dataAccess.abstracts.CommentRepository;
import com.project.questapp.dataAccess.abstracts.LikeRepository;
import com.project.questapp.dataAccess.abstracts.PostRepository;
import com.project.questapp.dataAccess.abstracts.UserRepository;
import com.project.questapp.entities.Comment;
import com.project.questapp.entities.Like;
import com.project.questapp.entities.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Service
@Data

public class UserManager implements UserService {
	
	private ModelMapperService modelMapperService;
	private UserRepository userRepository;
	private PostRepository postRepository;
	private LikeRepository likeRepository;
	private CommentRepository commentRepository;
	
	@Autowired
	public UserManager(ModelMapperService modelMapperService, UserRepository userRepository,
	                   PostRepository postRepository, LikeRepository likeRepository, 
	                   CommentRepository commentRepository) {
	    this.modelMapperService = modelMapperService;
	    this.userRepository = userRepository;
	    this.postRepository = postRepository;
	    this.likeRepository = likeRepository;
	    this.commentRepository = commentRepository;
	}
	
	

	@Override
	public List<GetAllUserResponse> getAll() {
		
		List<User> users = this.userRepository.findAll();
		List<GetAllUserResponse> userResponses = users.stream()
				.map(user->this.modelMapperService.forResponse()
				.map(user, GetAllUserResponse.class)).collect(Collectors.toList());
		
		return userResponses;
	}

	@Override
	public void addUser(CreateUserRequest newUser) {
		User user = this.modelMapperService.forRequest().map(newUser, User.class);
		this.userRepository.save(user);
		
	}
	
	public void saveUser(User user) {
	   this.userRepository.save(user);
	}

	@Override
	public GetByIdUserResponse getByIdUser(Long id) {
		User user = this.userRepository.findById(id).orElse(null);
		
		GetByIdUserResponse response = this.modelMapperService.forResponse().map(user, GetByIdUserResponse.class);
		return response;
	}

	@Override
	public void updateUser(Long id, UpdateUserRequest newUser) {
		
		 Optional<User> user = userRepository.findById(id);
		
		
			User user2 =user.get();	
			user2.setAvatar(newUser.getAvatar());
			
			
		 	this.userRepository.save(user2);
		 	
		
		
	}

	@Override
	public void deleteUser(Long id) {
		this.userRepository.deleteById(id);
		
	}

	public UserManager(ModelMapperService modelMapperService, UserRepository userRepository) {
		super();
		this.modelMapperService = modelMapperService;
		this.userRepository = userRepository;
	}

	@Override
	public User getOneUserByUserName(String userName) {
		
		return userRepository.findByUserName(userName);
	}

	@Override
	public List<Object> getUserActivity(Long userId) {
		
	  List<Long > postIds =	postRepository.findTopByUserId(userId);
	  if(postIds.isEmpty()) {
		  return null;}
	  List<Object> comments = commentRepository.findUserCommentByPostId(postIds);
	  List<Object> likes = likeRepository.findUserLikesByPostId(postIds);
	 
	  List<Object> result = new ArrayList<>();
	 
	  result.addAll(comments);
	  result.addAll(likes);
	  return result;
		
	}

}
