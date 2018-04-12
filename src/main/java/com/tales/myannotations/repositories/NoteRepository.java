package com.tales.myannotations.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tales.myannotations.domain.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note,Integer>{

}
