package com.tales.myannotations.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tales.myannotations.domain.User;

@RestController
@RequestMapping(value="/users")
public class UserResource {

	@RequestMapping(method=RequestMethod.GET)
	public List<User> list() {
		
		List<User> list1 = new ArrayList<>();
		
		User u1 = new User(1,"tales","tales@email","124432123","teste");
		User u2 = new User(2,"isabele","isabele@email","124432123","teste");
		User u3 = new User(3,"gustavo","gustavo@email","124432123","teste");
		User u4 = new User(4,"adriana","adriana@email","124432123","teste");
		
		list1.add(u1);
		list1.add(u2);
		list1.add(u3);
		list1.add(u4);
		
		return list1;

	}
	
	
}
