package com.project.questapp.business.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePostRequest {
	@NotNull
	@NotBlank
	private Long id;
	private Long userId;
	private String title;
	private String text;

}
