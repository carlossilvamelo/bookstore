package com.api.bookstore.dto;

import java.io.Serializable;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.api.bookstore.models.Credential;
import com.api.bookstore.models.User;

public class UserDto implements Serializable {

	private static final long serialVersionUID = 2292351380455928825L;

	private Long id;
	private String name;
	private String socialId;
	private String email;
	private String userName;
	private String password;
	private boolean isAdmin;

	public UserDto() {
		super();
	}

	public UserDto(String name, String socialId, String email, String userName, String password) {
		super();
		this.name = name;
		this.socialId = socialId;
		this.email = email;
		this.userName = userName;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public User toUser() {
		Credential credential = new Credential(new BCryptPasswordEncoder().encode(this.password), this.userName,
				this.isAdmin);
		User user = new User(credential, this.name, this.socialId, this.email, null);
		return user;
	}

}
