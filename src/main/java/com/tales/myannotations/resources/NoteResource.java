package com.tales.myannotations.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tales.myannotations.domain.Note;
import com.tales.myannotations.dto.NewNoteDTO;
import com.tales.myannotations.services.NoteService;

@RestController
@RequestMapping(value = "/notes")
public class NoteResource {

	@Autowired
	private NoteService noteservice;
	
//	@PreAuthorize("hasAnyRole('ADMIN')")
//	@RequestMapping(method = RequestMethod.GET)
//	ResponseEntity<List<Note>> findAll() {
//		List<Note> notes = noteservice.findAll();
//		return ResponseEntity.ok().body(notes);
//	}
	
	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<Note>> findByUser() {
		List<Note> notes = noteservice.findByUser();
		return ResponseEntity.ok().body(notes);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Note obj = noteservice.find(id);
		return ResponseEntity.ok().body(obj);

	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody NewNoteDTO obj) {
		Note note = noteservice.fromDTO(obj);
		note = noteservice.insert(note);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Note obj, @PathVariable Integer id) {
		obj.setId(id);
		noteservice.update(obj);
		return ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		noteservice.delete(id);
		return ResponseEntity.noContent().build();

	}

}
