package com.tales.myannotations.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tales.myannotations.domain.User;
import com.tales.myannotations.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;

	public User search(Integer id) {
		return repo.findOne(id);
	}
	
	public List<User>findAll(){
		return repo.findAll();
	}

	public void insert() {
		// TODO Auto-generated method stub
		
	}

}
