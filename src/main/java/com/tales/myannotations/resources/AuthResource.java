package com.tales.myannotations.resources;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tales.myannotations.security.JWTUtil;
import com.tales.myannotations.security.UserSS;
import com.tales.myannotations.services.UserSSService;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {
	@Autowired
	private JWTUtil jwtUtil;


	@RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		UserSS userss = UserSSService.authenticated();
		String token = jwtUtil.generateToken(userss.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		response.addHeader("access-control-expose-headers", "Authorization");
		return ResponseEntity.noContent().build();
	}

}
