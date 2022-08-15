package com.heitor.projeto.config;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.heitor.projeto.domain.Usuario;
import com.heitor.projeto.repository.UsuarioRepositoty;


@Configuration
@Profile("dev")
public class ProfileDev {

	private final UsuarioRepositoty repositoty;
	
	private final Logger LOG = LoggerFactory.getLogger(ProfileDev.class);
		
	@Autowired
	public ProfileDev(UsuarioRepositoty repositoty) {
		this.repositoty = repositoty;
	}
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strateg;
	
	@Bean
	public void startDB() {
		
		LOG.info("PROFILE DEV CARREGANDO");
		LOG.info("ESTRATEGIA DE BANCO: " + strateg.toUpperCase());
		
		Usuario usu1 = new Usuario(null, "Antonio Fernando", "anto@email.com", "123000");
		Usuario usu2 = new Usuario(null, "Orlando Lero Lis", "liss@email.com", "125780");
		Usuario usu3 = new Usuario(null, "Magda Timoteso F", "magg@email.com", "587895");
		
		repositoty.saveAll(List.of(usu1, usu2, usu3));
		
		
	}
}
