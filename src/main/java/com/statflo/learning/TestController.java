package com.statflo.learning;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	private Map<Integer, User> userMap = new HashMap<>();
	
	
	public TestController() {
		User user = User.builder().id(1).userName("test").build();
		userMap.put(1, user);
	}
	
	
	@GetMapping("/hello")
	public String hello( String id) {
		return "Hello World!";
	}
	
	@GetMapping("/hello/{id}")
	public ResponseEntity<String> hello(@PathVariable Integer id) {
		
		if (id==0) {
			//return ResponseEntity.notFound().build();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		else if (id<0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return ResponseEntity.ok("Hello World - " + id);
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<User> user(@PathVariable Integer id) {
		
		if (userMap.containsKey(id)) {
			return ResponseEntity.ok(userMap.get(id));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); 
	}
	
	@PostMapping("/users/")
	public ResponseEntity<User> user(@Valid @RequestBody User user) {
		userMap.put(user.getId(), user);
		return ResponseEntity.status(HttpStatus.CREATED).build(); 
	}
	
	@DeleteMapping("/users/{user_id}")
	public ResponseEntity<User> deleteuser(@PathVariable("user_id") Integer id) {
		
		if (!userMap.containsKey(id)) {
			return ResponseEntity.badRequest().build();
		}
		userMap.remove(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); 
	}
	
	@PutMapping("/users/{user_id}")
	public ResponseEntity<Void> updateUser(@RequestBody User user) {
		if (!userMap.containsKey(user.getId())) {
			return ResponseEntity.badRequest().build();
		}
		userMap.put(user.getId(), user);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); 
	}
	
	
	
	
	
	
	
	

}
