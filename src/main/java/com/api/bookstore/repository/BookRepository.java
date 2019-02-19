package com.api.bookstore.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.api.bookstore.models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	@Query("SELECT b FROM Book b " + "WHERE UPPER(b.title) LIKE UPPER(CONCAT('%',:title,'%')) "
			+ "AND UPPER(b.author) LIKE UPPER(CONCAT('%',:author,'%'))")
	public List<Book> findWithFilter(@Param("title") String title, @Param("author") String author, Pageable page);

	public void deleteById(Long id);
}
