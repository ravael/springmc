package br.com.springmc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.springmc.services.DBService;

@Configuration
@Profile("test")
public class TestEnvironmentConfig {
	
	@Autowired
	private DBService dbService;

	@Bean
	public boolean initiateDatabse() {
		try {
			dbService.initiateDatabase();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return true;
	}
}
