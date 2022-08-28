package com.heitor.projeto.resources;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.heitor.projeto.builder.UsuarioBuilder;
import com.heitor.projeto.domain.Usuario;
import com.heitor.projeto.domain.dto.UsuarioDTO;
import com.heitor.projeto.mapper.UsuarioMapper;
import com.heitor.projeto.repository.UsuarioRepositoty;
import com.heitor.projeto.resouces.UsuarioResources;
import com.heitor.projeto.services.UsuarioService;

@SpringBootTest
public class UsuarioResourcesTest {

	@InjectMocks
	private UsuarioResources resources;
	@Mock
	private UsuarioService usuarioService;
	@Mock
	private UsuarioRepositoty repositoty;
	@Mock
	private UsuarioMapper usuMapper = UsuarioMapper.INSTANCE;
	
	private UsuarioDTO usuarioDto;
	private Optional<Usuario> usuarioOptional;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		startEntidades();
	}
	
	@Test
	void quando_salvar_usuario() {
		Mockito.when(usuarioService.newUser(Mockito.any())).thenReturn(usuarioDto);

		ResponseEntity<UsuarioDTO> dto = resources.save(usuarioDto);
		
		Assertions.assertNotNull(dto);
		Assertions.assertEquals(HttpStatus.CREATED, dto.getStatusCode());
		
	}
	
	@Test
	void quando_chamar_find_by_id_com_retorno_sucesso() {
		
		Mockito.when(usuarioService.findById(Mockito.anyLong())).thenReturn(usuarioDto);
		Mockito.when(repositoty.findById(Mockito.anyLong())).thenReturn(usuarioOptional);
		Mockito.when(usuMapper.toDto(Mockito.any())).thenReturn(UsuarioBuilder.criarObjetDto());
		
		ResponseEntity<UsuarioDTO> response = resources.findById(UsuarioBuilder.ID);
		
		Assertions.assertNotNull(response);
		Assertions.assertNotNull(response.getBody());
		Assertions.assertEquals(ResponseEntity.class, response.getClass());
	}
	
	void startEntidades() {
		usuarioDto 		= UsuarioBuilder.criarObjetDto();
		usuarioOptional = UsuarioBuilder.criarOptional();
	}
}

