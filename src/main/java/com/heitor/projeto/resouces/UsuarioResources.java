package com.heitor.projeto.resouces;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.heitor.projeto.domain.dto.UsuarioDTO;
import com.heitor.projeto.services.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioResources {
	
	@Autowired
	private UsuarioService usuarioService;
	
//	@Autowired
//	public UsuarioResources(UsuarioService usuarioService) {
//		this.usuarioService = usuarioService;
//	}
	
	@PostMapping
	public ResponseEntity<UsuarioDTO> save(@RequestBody @Valid UsuarioDTO usuario){
		usuario = usuarioService.newUser(usuario);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping("/{idUsuario}")
	public ResponseEntity<UsuarioDTO> findById(@PathVariable(value = "idUsuario") Long idUsuario){
		UsuarioDTO usuarioDTO = usuarioService.findById(idUsuario);
		return ResponseEntity.ok(usuarioDTO);
	}
	
	@PutMapping("/{idUsuario}")
	public ResponseEntity<UsuarioDTO> update(@PathVariable(value = "idUsuario") Long idUsuario, @RequestBody UsuarioDTO usuarioDTO){
		usuarioDTO = usuarioService.update(idUsuario, usuarioDTO);
		return ResponseEntity.ok(usuarioDTO);
	}
	
	@DeleteMapping("/{idUsuario}")
	public ResponseEntity<?> deleteUsuario(@PathVariable(value = "idUsuario") Long idUsuario){
		usuarioService.deleteUsuario(idUsuario);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> findAll(){
		return ResponseEntity.ok(usuarioService.findAll());
	}


}
