package com.api.bookstore.config;

import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//	@Autowired
//	private DataSource dataSource;
//
//	@Override
//	protected void configure(HttpSecurity httpSecurity) throws Exception {
//		httpSecurity.csrf().disable().authorizeRequests().antMatchers("/home").permitAll().antMatchers("/h2/**")
//				.permitAll()
//				.antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources", "/configuration/security",
//						"/swagger-ui.html", "/webjars/**")
//				.permitAll().antMatchers(HttpMethod.POST, "/login").permitAll().anyRequest().authenticated().and()
//
//				// filtra requisições de login
//				.addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
//						UsernamePasswordAuthenticationFilter.class)
//				// filtra outras requisições para verificar a presença do JWT no header
//				.addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
//
//		httpSecurity.headers().frameOptions().disable(); // just to acess h2 console
//	}
//
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.jdbcAuthentication().dataSource(dataSource)
//        .usersByUsernameQuery("select username, password, enabled"
//            + " from users where username=?")
//        .authoritiesByUsernameQuery("select username, authority "
//            + "from authorities where username=?")
//        .passwordEncoder(new BCryptPasswordEncoder());
//	}
//}
