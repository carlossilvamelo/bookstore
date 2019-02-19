package com.api.bookstore.api;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import com.api.bookstore.models.User;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserResourceTest {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@TestConfiguration
	static class config {
		@Bean
		public RestTemplateBuilder restTemplateBuilder() {
			return new RestTemplateBuilder().basicAuthentication("admin", "admin");
		}
	}

	@Test
	public void tryListUserWithWrongCredentials() {
		ResponseEntity<User[]> response = testRestTemplate.getForEntity("/users", User[].class);
		Assertions.assertThat(response.getBody().length == 2);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
	}

}
