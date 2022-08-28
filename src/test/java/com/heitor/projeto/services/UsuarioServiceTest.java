package com.heitor.projeto.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import com.heitor.projeto.builder.UsuarioBuilder;
import com.heitor.projeto.domain.Usuario;
import com.heitor.projeto.domain.dto.UsuarioDTO;
import com.heitor.projeto.mapper.UsuarioMapper;
import com.heitor.projeto.repository.UsuarioRepositoty;

@SpringBootTest
public class UsuarioServiceTest {
	

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
		
		UsuarioDTO usuarioDTO = service.findById(UsuarioBuilder.ID);
		
		assertNotNull(usuarioDTO);
		assertEquals(UsuarioDTO.class, usuarioDTO.getClass());
		assertEquals(UsuarioBuilder.ID, usuarioDTO.getId());
		assertEquals(UsuarioBuilder.NOME, usuarioDTO.getName());
		assertEquals(UsuarioBuilder.EMAIL, usuarioDTO.getEmail());
		
	}
	
	@Test
	void quando_chamar_findById_nao_voltar_usuario() {
		Mockito.when(repositoty.findById(UsuarioBuilder.ID)).thenThrow(new NoSuchElementException("No value present"));
		
		try {
			service.findById(UsuarioBuilder.ID);
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
		
		String nome  = "Novo Nome usuario";
		String email = "novo.email@email.com";
		String senha = "12223344";
		
		usuario.setName(nome);
		usuario.setEmail(email);
		usuario.setPassword(senha);
		
		usuarioDto.setName(nome);
		usuarioDto.setEmail(email);
		usuarioDto.setPassword(senha);
		
		UsuarioDTO response = service.newUser(usuarioDto);
		
		assertNotNull(response);
		assertEquals(nome, response.getName());
		assertEquals(email, response.getEmail());
		assertEquals(senha, response.getPassword());
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
	
	@Test
	public void quando_atalizar_usuario() {
		Mockito.when(repositoty.findById(Mockito.anyLong())).thenReturn(usuarioOtional);
		Mockito.when(repositoty.save(Mockito.any())).thenReturn(usuario);
		Mockito.when(usuMapper.toDto(Mockito.any())).thenReturn(usuarioDto);
		
		usuarioDto.setName(UsuarioBuilder.NOME_ATUALIZA);
		usuarioDto.setPassword("11223344");
		UsuarioDTO response = service.update(UsuarioBuilder.ID, usuarioDto);
		
		assertNotNull(response);
		assertEquals(UsuarioDTO.class, response.getClass());
		assertEquals(UsuarioBuilder.NOME_ATUALIZA, response.getName());
		assertEquals(response.getPassword(), usuarioDto.getPassword());
	}
	
	@Test
	public void quando_deletar_usuario() {
		Mockito.when(repositoty.findById(Mockito.anyLong())).thenReturn(usuarioOtional);
		Mockito.when(usuMapper.toModel(Mockito.any())).thenReturn(usuario);
		Mockito.doNothing().when(repositoty).delete(Mockito.any());
		
		service.deleteUsuario(UsuarioBuilder.ID);
		
		Mockito.verify(repositoty, times(1)).delete(Mockito.any());
	}
	
	@Test
	public void quando_verificar_email_ja_cadastrado() {
		Mockito.when(repositoty.findById(Mockito.anyLong())).thenReturn(usuarioOtional);
		Mockito.when(repositoty.findByEmail(Mockito.anyString())).thenReturn(usuario);
		
		usuario.setId(UsuarioBuilder.ID + 1);
		
		try {
			service.newUser(usuarioDto);
		}catch (Exception e) {
			assertEquals(DataIntegrityViolationException.class, e.getClass());
			assertEquals("Email já cadastrado", e.getMessage());
		}
	
	}
	
	private void startUsers() {
		usuario = UsuarioBuilder.criarObjeto();
		usuarioDto = UsuarioBuilder.criarObjetDto();
		usuarioOtional = Optional.of(usuario);
	}
		
}
