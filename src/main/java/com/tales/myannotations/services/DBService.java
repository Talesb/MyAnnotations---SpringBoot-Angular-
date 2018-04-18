package com.tales.myannotations.services;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tales.myannotations.domain.Note;
import com.tales.myannotations.domain.User;
import com.tales.myannotations.enums.Perfil;
import com.tales.myannotations.repositories.NoteRepository;
import com.tales.myannotations.repositories.UserRepository;

@Service
public class DBService {

	@Autowired
	private UserRepository repo;

	@Autowired
	private NoteRepository noterepository;

	@Autowired
	private BCryptPasswordEncoder pe;

	public void instantiateDatabase() {
		
		User u1 = new User(null, "tales", "1235643", "tales@email.com", pe.encode("senha"));
		User u2 = new User(null, "isabele", "1235643", "isabele@email.com", pe.encode("senha"));
		User u3 = new User(null, "gustavo", "1235643", "gustavo@email.com", pe.encode("senha"));
		User u4 = new User(null, "adriana", "1235643", "adriana@email.com", pe.encode("senha"));

		u1.AddPerfil(Perfil.ADMIN);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		
		Note n1 = new Note(null, "Clean the Car", "Clean the car and Wash the tires.");
		Note n2 = new Note(null, "Clean the house", "Clean the house and take care of the dog.");
		Note n3 = new Note(null, "Rest in the sofa", "Rest in sofa and take some juice.");
		Note n4 = new Note(null, "Study science", "Study chremist and physics.");
		Note n5 = new Note(null, "Play Call of duty", "Play call of duty on PS3.");
		Note n6 = new Note(null, "Help with the dinner", "Help with the dinner,cooking a lot.");
		Note n7 = new Note(null, "Study java and Javascript",
				"Study java all the day, and study javascript all night.");
		Note n8 = new Note(null, "Study math", "Study math a lot");

		u1.getNotes().addAll(Arrays.asList(n1, n7));
		u2.getNotes().addAll(Arrays.asList(n5, n6));
		u3.getNotes().addAll(Arrays.asList(n8, n4));
		u4.getNotes().addAll(Arrays.asList(n3, n2));

		n1.setUser(u1);
		n2.setUser(u4);
		n3.setUser(u4);
		n4.setUser(u3);
		n5.setUser(u2);
		n6.setUser(u2);
		n7.setUser(u1);
		n8.setUser(u3);

		repo.save(Arrays.asList(u1, u2, u3, u4));
		noterepository.save(Arrays.asList(n1, n2, n3, n4, n5, n6, n7, n8));

	}

}
