package com.api.bookstore.repository;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.api.bookstore.enums.Category;
import com.api.bookstore.models.Book;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {

	@Autowired
	private BookRepository bookRepository;

	@Test
	public void createTest() {
		Book book = new Book("Lorem ipsum", "ipsum dolor sit amet", "adipiscing elit", true, null, Category.FANTASY);
		book = bookRepository.save(book);
		Assertions.assertThat(book).isNotNull();
		Assertions.assertThat(book.getTitle()).isEqualTo("Lorem ipsum");
	}
}
