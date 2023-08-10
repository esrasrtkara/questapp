package com.project.questapp.business.abstracts;

import java.util.List;

import com.project.questapp.business.requests.CreateUserRequest;
import com.project.questapp.business.requests.UpdateUserRequest;
import com.project.questapp.business.responses.GetAllUserResponse;
import com.project.questapp.business.responses.GetByIdUserResponse;
import com.project.questapp.entities.User;

public interface UserService {
	
	List<GetAllUserResponse> getAll();
	void addUser(CreateUserRequest newUser);
	GetByIdUserResponse getByIdUser(Long id);
    void updateUser(Long id , UpdateUserRequest newUser);
	void deleteUser(Long id);
	User getOneUserByUserName(String userName);
	List<Object> getUserActivity(Long userId);
	void saveUser(User user) ;

}
