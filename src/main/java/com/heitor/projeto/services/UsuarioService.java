package com.heitor.projeto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heitor.projeto.domain.Usuario;
import com.heitor.projeto.repository.UsuarioRepositoty;

@Service
public class UsuarioService {

	private final UsuarioRepositoty repositoty;
	
	@Autowired
	public UsuarioService(UsuarioRepositoty repositoty) {
		this.repositoty = repositoty;
	}
	
	public Usuario save(Usuario usuario) {
		usuario = repositoty.save(usuario);
		return usuario;
	}
	
	public Usuario findById(Long idUsuario) {
		Usuario usuario = repositoty.findById(idUsuario).orElseThrow();
		return usuario;
	}
}
