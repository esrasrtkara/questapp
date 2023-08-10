package com.project.questapp.business.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;


@Data
public class CreateUserRequest {
	@NotNull
	@NotBlank
	@Size(min = 3, max = 20)
	private String userName;
	private String password;

}