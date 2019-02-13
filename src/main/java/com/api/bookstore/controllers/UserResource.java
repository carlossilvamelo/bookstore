package com.api.bookstore.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.bookstore.dto.LoginDto;
import com.api.bookstore.dto.UserDto;
import com.api.bookstore.models.Address;
import com.api.bookstore.models.Credential;
import com.api.bookstore.models.User;
import com.api.bookstore.services.CredentialService;
import com.api.bookstore.services.JwtManager;
import com.api.bookstore.services.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user")
@Api(value = "Greeting", description = "Greeting people")
public class UserResource {
	
	static final String TOKEN_ISSUER = "issue";
    static final String TOKEN_PARAMETER = "token";
    static final String TOKEN_SUBJECT = "sub";
    static final int TIMEOUT = 1440*60*1000;//24 Hours
    
	@Autowired
	private UserService userService;
	
	@Autowired
	private CredentialService credentialService;
	
	@ApiOperation(value = "api")
	@PostMapping("/login")
	public ResponseEntity<UserDto> login(@RequestBody LoginDto login){
		//Credential credential = credentialService.getById(id)
		User user = credentialService.verifyUser(login.getUserName(),login.getPassword());
		UserDto userDto = null;
		if(user != null) {
			
			userDto = new UserDto(user);
			userDto.setToken(JwtManager.createJWT(user.getCredential().getUserName(), 
					"issue", 
					TOKEN_SUBJECT, 
					TIMEOUT));
		}
		return user != null ? ResponseEntity.ok().body(userDto):
			ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
	
	@PostMapping("")
	public ResponseEntity<User> create(@Valid @RequestBody User user) {
		userService.create(user);
		
		return ResponseEntity.noContent().build();
	}
	@PostMapping("{userId}")
	public ResponseEntity<User> signup(@PathVariable Long userId,@Valid @RequestBody Credential credential) {
	
		User user = userService.getById(userId);
		if(user != null && credential != null) {
			if(user.getCredential()!= null) {
				user.getCredential().setUserName(credential.getUserName());
				user.getCredential().setPassword(credential.getPassword());
				userService.update(user);
				return ResponseEntity.ok(user);
			}else {
				user.setCredential(credential);
				userService.create(user);
				return ResponseEntity.ok(user);
			}
			
		}
		
		return ResponseEntity.badRequest().build();
	}
	
	@GetMapping("obj")
	public ResponseEntity<User> getObj(){
		Credential credential = new Credential("admin", "admin");
		User user = new User(credential, "Admin admin", "09006369470", "admin@admin.com",
				new Address("consectetur adipiscing elit", "sed semper urna hendrerit quis", 3000, 52110000,
						"suscipit sed venenatis est"));
		
		return ResponseEntity.ok().body(user);
	}
	
	@GetMapping("")
	public ResponseEntity<List<User>> getAll(){
		
		List<User> userList = userService.getAll();
		userList.forEach(x ->System.out.println(x.getName()));
		System.out.println(userList.size());
		return ResponseEntity.ok().body(userList);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getById(@PathVariable Long id){
		User user = userService.getById(id);
		
		
		return user != null? ResponseEntity.ok().body(user): ResponseEntity.notFound().build();
	}
	
	
	
}
