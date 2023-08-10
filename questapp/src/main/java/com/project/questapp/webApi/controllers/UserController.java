package com.project.questapp.webApi.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.questapp.business.abstracts.UserService;
import com.project.questapp.business.requests.CreateUserRequest;
import com.project.questapp.business.requests.UpdateUserRequest;
import com.project.questapp.business.responses.GetAllUserResponse;
import com.project.questapp.business.responses.GetByIdUserResponse;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
	
	private UserService userService;
	
	@GetMapping
	public List<GetAllUserResponse> getAll(){
		
		return this.userService.getAll();
	}
	@PostMapping()
	@ResponseStatus(code=HttpStatus.CREATED)
	public void addUser(CreateUserRequest newUser) {
		
		 this.userService.addUser(newUser);
	}
	
	@GetMapping("/{id}")
	public GetByIdUserResponse getByIdUser(@PathVariable Long id) {
		return this.userService.getByIdUser(id);
	}
	@PutMapping("/{id}")
	public void updateUser(@PathVariable Long id, @RequestBody UpdateUserRequest newUser) {
		
		this.userService.updateUser(id,newUser);
		
	}
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable Long id) {
		
		this.userService.deleteUser(id);
		
	}
	@GetMapping("/activity/{userId}")
	public List<Object> getUserActivity(@PathVariable Long userId){
	return	userService.getUserActivity(userId);
		
	}

}
