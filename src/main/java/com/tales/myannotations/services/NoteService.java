package com.tales.myannotations.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tales.myannotations.domain.Note;
import com.tales.myannotations.domain.User;
import com.tales.myannotations.dto.NewNoteDTO;
import com.tales.myannotations.enums.Perfil;
import com.tales.myannotations.repositories.NoteRepository;
import com.tales.myannotations.repositories.UserRepository;
import com.tales.myannotations.security.UserSS;
import com.tales.myannotations.services.exception.AuthorizationException;
import com.tales.myannotations.services.exception.ObjectNotFoundException;

@Service
public class NoteService {

	@Autowired
	private NoteRepository repo;
	
	@Autowired
	private UserRepository userrepo;

	@Autowired
	private UserService userservice;

	public Note find(Integer id) {
		
		UserSS userss = UserSSService.authenticated();
		if (userss == null) {
			throw new AuthorizationException("User not Autorizated");
		}
		Note obj = repo.findOne(id);
		if(obj==null ) {
			throw new ObjectNotFoundException("User not Found");
		}
		if(obj.getUser().getId()!=userss.getId()) {
//			System.out.println("========================="+obj.getUser().getId()+"==============="+userss.getId());
			throw new AuthorizationException("User not Autorizated");
		}
		return obj;
	}

	public List<Note> findAll() {
		return repo.findAll();
	}
	
	public List<Note> findByUser(){
		UserSS userss = UserSSService.authenticated();
		if (userss == null ) {
			throw new AuthorizationException("User not Autorizated");
		}
		List<Note> notelist = new ArrayList<>();
		User user = userrepo.findOne(userss.getId());
		notelist = repo.findByUser(user);
		return notelist;
		 
	}

	public Note insert(Note obj) {
		obj.setId(null);
		repo.save(obj);
		User u1 = userservice.find(obj.getUser().getId());
		u1.getNotes().addAll(Arrays.asList(obj));
		userservice.update(u1);
		return obj;

	}

	public void update(Note obj) {
		try {
			Note aux = find(obj.getId());
			obj.setUser(aux.getUser());
			if (obj.getName() != null) {
				repo.updatename(obj.getId(), obj.getUser().getId(), obj.getName());
			}
			if (obj.getContent() != null) {
				repo.updatecontent(obj.getId(), obj.getUser().getId(), obj.getContent());
			}
			repo.updatedata(obj.getId(), obj.getUser().getId(), new Date());
		} catch (Exception e) {
			throw new RuntimeException("Error:" + e.getMessage());
		}
	}

	public void delete(Integer id) {
		find(id);
		repo.delete(id);

	}

	public Note fromDTO(NewNoteDTO noteDto) {
		Note n1 = new Note(noteDto.getId(),noteDto.getName(),noteDto.getData(),noteDto.getContent());
		n1.setUser(userservice.find(noteDto.getUserid()));
		return n1;
}
}
