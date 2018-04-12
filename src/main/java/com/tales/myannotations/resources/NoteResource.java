package com.tales.myannotations.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tales.myannotations.domain.Note;
import com.tales.myannotations.domain.User;
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
		Note obj = noteservice.find(id);
		return ResponseEntity.ok().body(obj);

	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Note obj) {
		obj = noteservice.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	@RequestMapping(value ="/{id}",method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Note obj,@PathVariable Integer id){
		obj.setId(id);
		obj=noteservice.update(obj);
		return ResponseEntity.noContent().build();
	}

}
	
	

