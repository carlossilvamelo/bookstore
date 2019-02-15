package com.api.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.bookstore.models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{



	@Query("SELECT b FROM Book b "
			+ "WHERE UPPER(b.title) LIKE UPPER(CONCAT('%',:title,'%')) "
			+ "AND UPPER(b.author) LIKE UPPER(CONCAT('%',:author,'%')) "
			+ "ORDER BY "
			+ "CASE "
			+ "WHEN 'title' = :sortby THEN b.title "
			+ "WHEN 'author' = :sortby THEN b.author "
			+ "END ASC")
	public List<Book> findWithFilter(@Param("title")String title, @Param("author")String author, @Param("sortby")String sortBy);
}
