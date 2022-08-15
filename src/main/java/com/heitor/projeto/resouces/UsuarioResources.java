package com.heitor.projeto.resouces;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.heitor.projeto.domain.Usuario;
import com.heitor.projeto.domain.dto.UsuarioDTO;
import com.heitor.projeto.services.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioResources {
	
	private final UsuarioService usuarioService;
	
	@Autowired
	public UsuarioResources(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@PostMapping
	public ResponseEntity<Usuario> save(@RequestBody @Valid Usuario usuario){
		usuario = usuarioService.save(usuario);
		return ResponseEntity.ok(usuario);
	}
	
	@GetMapping("/{idUsuario}")
	public ResponseEntity<UsuarioDTO> findById(@PathVariable(value = "idUsuario") Long idUsuario){
		UsuarioDTO usuarioDTO = usuarioService.findById(idUsuario);
		return ResponseEntity.ok(usuarioDTO);
	}
	


}
