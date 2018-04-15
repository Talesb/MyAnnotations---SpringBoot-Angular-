package com.tales.myannotations.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tales.myannotations.domain.User;
import com.tales.myannotations.repositories.UserRepository;
import com.tales.myannotations.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
     User u = repo.findByEmail(email);
     if(u==null) {
    	 throw new UsernameNotFoundException("Email");
     }
		return new UserSS(u.getId(),u.getEmail(),u.getPassword(),u.getPerfils());
	}

}
