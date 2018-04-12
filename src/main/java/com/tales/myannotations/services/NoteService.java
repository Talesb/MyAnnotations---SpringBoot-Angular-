package com.tales.myannotations.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tales.myannotations.domain.Note;
import com.tales.myannotations.repositories.NoteRepository;

@Service
public class NoteService {

	@Autowired
	private NoteRepository repo;

	public Note find(Integer id) {
		return repo.findOne(id);
	}
	
	public List<Note>findAll(){
		return repo.findAll();
	}

	public Note insert(Note obj) {
		obj.setId(null);
		repo.save(obj);
		return obj;

	}

	public Note update(Note obj) {
		find(obj.getId());

		return repo.save(obj);
	}
}
