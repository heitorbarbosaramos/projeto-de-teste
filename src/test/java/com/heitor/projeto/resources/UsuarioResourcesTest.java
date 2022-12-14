package com.heitor.projeto.resources;

import java.util.ArrayList;
import java.util.List;
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
	private List<UsuarioDTO> listUsuarioDto;

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

	@Test
	void quando_atualizar() {

		Mockito.when(usuarioService.update(Mockito.anyLong(), Mockito.any())).thenReturn(usuarioDto);

		ResponseEntity<UsuarioDTO> response = resources.update(1l, usuarioDto);

		Assertions.assertNotNull(response);
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals(UsuarioDTO.class, response.getBody().getClass());

	}

	@Test
	void quando_deletar() {

		Mockito.doNothing().when(usuarioService).deleteUsuario(Mockito.anyLong());

		ResponseEntity<?> response = resources.deleteUsuario(1l);

		Assertions.assertNotNull(response);
		Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

	}

	@Test
	void quando_buscar_todos() {

		Mockito.when(usuarioService.findAll()).thenReturn(listUsuarioDto);

		ResponseEntity<List<UsuarioDTO>> response = resources.findAll();

		Assertions.assertNotNull(response);
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals(ArrayList.class, response.getBody().getClass());
	}

	void startEntidades() {
		usuarioDto = UsuarioBuilder.criarObjetDto();
		usuarioOptional = UsuarioBuilder.criarOptional();

		listUsuarioDto = new ArrayList<>();
		listUsuarioDto.add(usuarioDto);
	}
}
