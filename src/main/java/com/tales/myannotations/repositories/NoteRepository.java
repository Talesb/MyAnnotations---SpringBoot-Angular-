package com.tales.myannotations.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tales.myannotations.domain.Note;
import com.tales.myannotations.domain.User;

@Repository
public interface NoteRepository extends JpaRepository<Note,Integer>{

	@Modifying
	@Transactional
	@Query("update Note n SET n.name =:name where n.id =:id and n.user.id =:uid")
	public void updatename(@Param("id") Integer id,@Param("uid") Integer uid,@Param("name") String name);
	
	@Modifying
	@Transactional
	@Query("update Note n SET n.data =:data where n.id =:id and n.user.id =:uid")
	public void updatedata(@Param("id") Integer id,@Param("uid") Integer uid,@Param("data") Date data);
	
	@Modifying
	@Transactional
	@Query("update Note n SET n.content =:content where n.id =:id and n.user.id =:uid")
	public void updatecontent(@Param("id") Integer id,@Param("uid") Integer uid,@Param("content") String name);

	@Transactional(readOnly = true)
	List<Note> findByUser(User user);
}
