package com.heitor.projeto.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

	private Usuario save(Usuario usuario) {
		usuario = repositoty.save(usuario);
		return usuario;
	}

	public UsuarioDTO newUser(UsuarioDTO usuarioDTO) {
		verificaEmailCadastrado(usuarioDTO);
		Usuario usuario = save(usuMapper.toModel(usuarioDTO));
		usuarioDTO = usuMapper.toDto(usuario);
		return usuarioDTO;
	}

	public UsuarioDTO findById(Long idUsuario) {
		Usuario usuario = repositoty.findById(idUsuario).orElseThrow();
		UsuarioDTO usuarioDTO = usuMapper.toDto(usuario);
		return usuarioDTO;
	}

	public UsuarioDTO update(Long idUsuario, UsuarioDTO usuarioDTO) {
		findById(idUsuario);
		usuarioDTO.setId(idUsuario);
		verificaEmailCadastrado(usuarioDTO);
		Usuario usuario =  save(usuMapper.toModel(usuarioDTO));
		usuarioDTO = usuMapper.toDto(usuario);
		return usuarioDTO;
	}

	public List<UsuarioDTO> findAll() {
		List<UsuarioDTO> dtos = repositoty.findAll().stream().map(x -> usuMapper.toDto(x)).collect(Collectors.toList());
		return dtos;
	}

	public void deleteUsuario(Long idUsuario) {
		repositoty.delete(usuMapper.toModel(findById(idUsuario)));
	}
	
	private void verificaEmailCadastrado(UsuarioDTO dto) {
		Usuario usuario = repositoty.findByEmail(dto.getEmail());
		if (usuario != null && !usuario.getId().equals(dto.getId()) ) {
			throw new DataIntegrityViolationException("Email já cadastrado");
		}

	}
}
