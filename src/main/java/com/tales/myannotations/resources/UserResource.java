package com.tales.myannotations.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tales.myannotations.domain.User;
import com.tales.myannotations.services.UserService;

@RestController
@RequestMapping(value="/users")
public class UserResource {

	@Autowired
	private UserService userservice;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<User> list() {
		
		 return userservice.findAll();	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public  ResponseEntity<?> find(@PathVariable Integer id) {
		User obj = userservice.search(id);
		return ResponseEntity.ok().body(obj);

	}
	
	
}
