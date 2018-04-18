package com.tales.myannotations.dto;

import java.io.Serializable;
import java.util.Date;

import com.tales.myannotations.domain.Note;



public class NewNoteDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String content;
	private Integer userid;
	
	
	public NewNoteDTO(Note note) {
		this.id = note.getId();
		this.name=note.getName();
		this.content =note.getContent();
		this.userid = note.getUser().getId();
	}
	
	
	
	public NewNoteDTO() {
	}



	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NewNoteDTO other = (NewNoteDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}



 
	
	
	}
	
	
	

