package br.com.springmc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.springmc.services.DBService;

@Configuration
@Profile("dev")
public class DevEnvironmentConfig {

	@Autowired
	private DBService dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String mysql;

	@Bean
	public boolean initiateDatabse() {
		try {
			
			if(!"create".equals(mysql)) {
				return false;
			}
			
			dbService.initiateDatabase();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return true;
	}
}
