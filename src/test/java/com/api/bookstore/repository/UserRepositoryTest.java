package com.api.bookstore.repository;

import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.api.bookstore.models.User;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void createTest() {
		User user = new User(null, "username", "123", "user@user.com", null);
		user = this.userRepository.save(user);
		Assertions.assertThat(user).isNotNull();
		Assertions.assertThat(user.getName()).isEqualTo("username");
	}

	@Test
	public void findByIdTest() {
		User user = this.userRepository.findById(1L).get();
		Assertions.assertThat(user).isNotNull();
		Assertions.assertThat(user.getId()).isEqualTo(1L);
	}

	@Test
	public void findUserByCredentials() {
		// this.userRepository.findAll().forEach(x->System.out.println(x.getName()));
//		User user = this.userRepository.findUserByCredentials("admin", "admin");
//		System.out.println(user.getCredential().getPassword());
//		Assertions.assertThat(user).isNotNull();
//		Assertions.assertThat(user.getCredential().getPassword())
//				.isEqualTo(new BCryptPasswordEncoder().encode("admin"));
	}
}
