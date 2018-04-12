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

	public Note search(Integer id) {
		return repo.findOne(id);
	}
	
	public List<Note>findAll(){
		return repo.findAll();
	}

	public void insert() {
		 
		
	}

}
