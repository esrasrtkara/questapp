package com.project.questapp.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	private String userName;
	private String password;
	private int avatar ;
	
	@OneToMany(mappedBy = "user")
	private List<Post> posts;
	@OneToMany(mappedBy = "user")
	private List<Like> likes;
	@OneToMany(mappedBy = "user")
	private List<Comment> comments;
}
