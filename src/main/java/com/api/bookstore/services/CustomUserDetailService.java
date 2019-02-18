package com.api.bookstore.services;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.api.bookstore.models.Credential;
import com.api.bookstore.repository.CredentialRepository;



@Service
public class CustomUserDetailService implements UserDetailsService{

	private final CredentialRepository credentialRepository;
	
	@Autowired
	public CustomUserDetailService(CredentialRepository credentialRepository) {
		this.credentialRepository = credentialRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Credential credential = Optional.ofNullable(credentialRepository
				.findByUserName(username))
				.orElseThrow(()-> new UsernameNotFoundException("User not found"));
		
		List<GrantedAuthority> rolesAdmin = AuthorityUtils.createAuthorityList("ROLE_USER","ROLE_ADMIN");
		List<GrantedAuthority> rolesUser = AuthorityUtils.createAuthorityList("ROLE_USER");
		return new User(credential.getUserName(), credential.getPassword()
				,credential.isAdmin() ? rolesAdmin : rolesUser);
	}


}
