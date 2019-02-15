package com.api.bookstore.models;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="USER")
public class User {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private Long id;
	
	@OneToOne(cascade = CascadeType.ALL)
    private Credential credential;
	@Column(name = "name")
	private String name;
	@Column(name = "social_id")
	private String socialId;
	@Column(name = "email")
	private String email;
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;
	
	public User() {}
	
	
	public User(Credential credential, String name, String socialId, String email, Address address) {
		super();
		this.credential = credential;
		this.name = name;
		this.socialId = socialId;
		this.email = email;
		this.address = address;
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
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}


	public Credential getCredential() {
		return credential;
	}


	public void setCredential(Credential credential) {
		this.credential = credential;
	}
	
	

}
