package com.tales.myannotations.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tales.myannotations.domain.User;
import com.tales.myannotations.repositories.UserRepository;
import com.tales.myannotations.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;

	public User find(Integer id) {
		User obj = repo.findOne(id);

		if (obj == null) {
			throw new ObjectNotFoundException("User not Found");
		}

		return obj;
	}

	public List<User> findAll() {
		return repo.findAll();
	}

	public User insert(User obj) {
		obj.setId(null);
		repo.save(obj);
		return obj;

	}

	public User update(User obj) {
		find(obj.getId());

		return repo.save(obj);
	}

	public void delete(Integer id) {
		find(id);
		repo.delete(id);
		
	}

}
