package com.tales.myannotations.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.tales.myannotations.security.UserSS;

public class UserSSService {

	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception ex) {
			return null;
		}

	}

}
