package com.heitor.projeto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heitor.projeto.domain.Usuario;
import com.heitor.projeto.domain.dto.UsuarioDTO;
import com.heitor.projeto.mapper.UsuarioMapper;
import com.heitor.projeto.repository.UsuarioRepositoty;

@Service
public class UsuarioService {

	private final UsuarioRepositoty repositoty;
	
	private UsuarioMapper usuMapper = UsuarioMapper.INSTANCE;
	
	@Autowired
	public UsuarioService(UsuarioRepositoty repositoty) {
		this.repositoty = repositoty;
	}
	
	public Usuario save(Usuario usuario) {
		usuario = repositoty.save(usuario);
		return usuario;
	}
	
	public UsuarioDTO findById(Long idUsuario) {
		Usuario usuario = repositoty.findById(idUsuario).orElseThrow();
		UsuarioDTO usuarioDTO = usuMapper.toDto(usuario);
		return usuarioDTO;
	}
}
