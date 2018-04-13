package com.tales.myannotations.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tales.myannotations.domain.Note;
import com.tales.myannotations.repositories.NoteRepository;
import com.tales.myannotations.services.exception.ObjectNotFoundException;

@Service
public class NoteService {

	@Autowired
	private NoteRepository repo;

	public Note find(Integer id) {
		Note obj = repo.findOne(id);

		if (obj == null) {
			throw new ObjectNotFoundException("Note not Found");
		}

		return obj;
	}

	public List<Note> findAll() {
		return repo.findAll();
	}

	public Note insert(Note obj) {
		obj.setId(null);
		repo.save(obj);
		return obj;

	}

	public void update(Note obj) {
		try {
		Note aux = find(obj.getId());
		obj.setUser(aux.getUser());
		if(obj.getName()!=null) {
		 repo.updatename(obj.getId(),obj.getUser().getId(),obj.getName());}
		if(obj.getContent()!=null) {
			repo.updatecontent(obj.getId(),obj.getUser().getId(),obj.getContent());
		}
		repo.updatedata(obj.getId(),obj.getUser().getId(),new Date());
		} catch (Exception e) {
			throw new RuntimeException("Error:"+e.getMessage());
	}}

	public void delete(Integer id) {
		find(id);
		repo.delete(id);
		
	}

}
