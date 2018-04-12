package com.tales.myannotations.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tales.myannotations.domain.Note;
import com.tales.myannotations.services.NoteService;

@RestController
@RequestMapping(value="/notes")
public class NoteResource {

	@Autowired
	private NoteService noteservice;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Note> list() {
		
		 return noteservice.findAll();	}
	
	
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public  ResponseEntity<?> find(@PathVariable Integer id) {
		Note obj = noteservice.search(id);
		return ResponseEntity.ok().body(obj);

	}
	
	
}
