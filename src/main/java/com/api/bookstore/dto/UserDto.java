package com.api.bookstore.dto;

import java.io.Serializable;

import com.api.bookstore.models.User;

public class UserDto implements Serializable{

	private static final long serialVersionUID = 2292351380455928825L;
	
	private Long id;
	private String name;
	private String socialId;
	private String email;
	private String token;
	
	
	public UserDto(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.socialId = user.getSocialId();
		this.email = user.getEmail();
	}
	
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSocialId() {
		return socialId;
	}
	public void setSocialId(String socialId) {
		this.socialId = socialId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

}
