package com.api.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.bookstore.models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

	public List<Book> findByTitleContainingIgnoreCase(String title);
	public List<Book> findByAuthorContainingIgnoreCase(String author);
	public List<Book> findByCategory(Integer category);
}
