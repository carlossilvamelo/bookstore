package com.api.bookstore.repository;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.api.bookstore.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	 

	public List<User> findOneByNameContainingIgnoreCase(String name);
	
	public User findOneBySocialId(String socialId);
	
	@Query("SELECT u FROM User u INNER JOIN u.credential c WHERE c.userName = ?1 AND c.password = ?2")
	public User findUserByCredentials(String userName, String password);
}
