package com.heitor.projeto.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.heitor.projeto.domain.Usuario;
import com.heitor.projeto.domain.dto.UsuarioDTO;
import com.heitor.projeto.mapper.UsuarioMapper;
import com.heitor.projeto.repository.UsuarioRepositoty;

@SpringBootTest
public class UsuarioServiceTest {
	
	private final Long ID 		= 1l;
	private final String NOME 	= "Usuario Nome de Teste";
	private final String EMAIL 	= "email@email.com";
	private final String SENHA 	= "123";

	@InjectMocks
	private UsuarioService service;
	
	@Mock
	private UsuarioRepositoty repositoty;
	
	@Mock
	private UsuarioMapper usuMapper = UsuarioMapper.INSTANCE;
	
	private Usuario usuario;
	private UsuarioDTO usuarioDto;
	private Optional<Usuario> usuarioOtional;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		startUsers();
	}
	
	@Test
	void quando_chamar_findById(){
		Mockito.when(repositoty.findById(Mockito.anyLong())).thenReturn(usuarioOtional);
		Mockito.when(usuMapper.toDto(Mockito.spy(Usuario.class))).thenReturn(usuarioDto);
		
		UsuarioDTO usuarioDTO = service.findById(ID);
		
		assertNotNull(usuarioDTO);
		assertEquals(UsuarioDTO.class, usuarioDTO.getClass());
		assertEquals(ID, usuarioDTO.getId());
		assertEquals(NOME, usuarioDTO.getName());
		assertEquals(EMAIL, usuarioDTO.getEmail());
		
	}
	
	private void startUsers() {
		usuario = new Usuario(ID, NOME, EMAIL, SENHA);
		usuarioDto = new UsuarioDTO(ID, NOME, EMAIL, SENHA);
		usuarioOtional = Optional.of(new Usuario(ID, NOME, EMAIL, SENHA));
	}
	
	
}
