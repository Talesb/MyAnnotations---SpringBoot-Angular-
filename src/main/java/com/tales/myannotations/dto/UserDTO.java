package com.tales.myannotations.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.tales.myannotations.domain.User;

public class UserDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotEmpty(message="The field name cant be empty")
	@Length(min=5,message="Minimum 5 character")
	private String name;
	
	@NotEmpty(message="The field cpf cant be empty")
	private String cpf;
	
	@NotEmpty(message="The field email cant be empty")
	@Email(message="invalid email")
	private String email;
	
	@NotEmpty(message="The field password cant be empty")
	private String password;

	public UserDTO(User obj) {
		id = obj.getId();
		name = obj.getName();
		cpf = obj.getCpf();
		email = obj.getEmail();
		password = obj.getPassword();

	}

	public UserDTO() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
