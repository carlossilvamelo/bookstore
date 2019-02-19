package com.api.bookstore.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.bookstore.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public Page<User> findOneByNameContainingIgnoreCase(String name, Pageable pageRequest);

	public User findOneBySocialId(String socialId);

	@Query("SELECT u FROM User u INNER JOIN u.credential c WHERE c.userName = ?1 AND c.password = ?2")
	public User findUserByCredentials(String userName, String password);

}
