package com.tales.myannotations.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.tales.myannotations.services.DBService;

@Configuration
@Profile("dev")
public class DevConfig {

	@Autowired
	DBService db; 
	
	@Bean
	public boolean instantiateDatabase() {
		db.instantiateDatabase();
		return true;
	}
	
	
}
