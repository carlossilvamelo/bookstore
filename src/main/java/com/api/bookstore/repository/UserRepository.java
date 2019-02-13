package com.api.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.bookstore.models.Credential;
import com.api.bookstore.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	 

	public User findOneByCredential(Credential credential);
}
