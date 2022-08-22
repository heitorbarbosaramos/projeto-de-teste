package com.heitor.projeto.services;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

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
		Mockito.when(usuMapper.toDto(Mockito.any())).thenReturn(usuarioDto);
		
		UsuarioDTO usuarioDTO = service.findById(ID);
		
		assertNotNull(usuarioDTO);
		assertEquals(UsuarioDTO.class, usuarioDTO.getClass());
		assertEquals(ID, usuarioDTO.getId());
		assertEquals(NOME, usuarioDTO.getName());
		assertEquals(EMAIL, usuarioDTO.getEmail());
		
	}
	
	@Test
	void quando_chamar_findById_nao_voltar_usuario() {
		Mockito.when(repositoty.findById(ID)).thenThrow(new NoSuchElementException("No value present"));
		
		try {
			service.findById(ID);
		}catch (Exception e) {
			assertEquals(NoSuchElementException.class, e.getClass());
			assertEquals("No value present", e.getMessage());
		}

	}
	
	@Test
	public void quando_voltar_lista_de_usuario() {
		Mockito.when(repositoty.findAll()).thenReturn(List.of(usuario));
		Mockito.when(usuMapper.toDto(Mockito.any())).thenReturn(usuarioDto);
		
		List<UsuarioDTO> response = service.findAll();
		
		assertNotNull(response);
		assertEquals(UsuarioDTO.class, response.get(0).getClass());
		
	}
	
	@Test
	public void quando_criar_novo_usuario() {
		Mockito.when(repositoty.save(Mockito.any())).thenReturn(usuario);
		Mockito.when(usuMapper.toDto(Mockito.any())).thenReturn(usuarioDto);
		
		UsuarioDTO response = service.newUser(usuarioDto);
		
		assertNotNull(response);
	}
	
	@Test
	public void quando_criar_novo_usuario_der_ruim() {
		Mockito.when(repositoty.save(Mockito.any())).thenReturn(usuario);
		Mockito.when(usuMapper.toDto(Mockito.any())).thenReturn(usuarioDto);
		Mockito.when(repositoty.findByEmail(Mockito.anyString())).thenReturn(usuario);
		
		try {
			usuarioDto.setId(2L);
			service.newUser(usuarioDto);
		}catch (Exception e) {
			assertEquals(DataIntegrityViolationException.class, e.getClass());
			assertEquals("Email já cadastrado", e.getMessage());
		}
		
		
	}
	
	private void startUsers() {
		usuario = new Usuario(ID, NOME, EMAIL, SENHA);
		usuarioDto = new UsuarioDTO(ID, NOME, EMAIL, SENHA);
		usuarioOtional = Optional.of(new Usuario(ID, NOME, EMAIL, SENHA));
	}
	
	
}
