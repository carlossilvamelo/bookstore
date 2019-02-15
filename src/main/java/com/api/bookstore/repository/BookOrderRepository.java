package com.api.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.bookstore.models.BookOrder;
import com.api.bookstore.models.User;

@Repository
public interface BookOrderRepository extends JpaRepository<BookOrder, Long>{

	
	public BookOrder findOneByUser(User user);

	@Query("SELECT b FROM BookOrder b INNER JOIN b.user u WHERE u.id = ?1")
	public BookOrder findOrderByUserId(Long userId);
}
