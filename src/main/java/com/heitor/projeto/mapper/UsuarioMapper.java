package com.heitor.projeto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.heitor.projeto.domain.Usuario;
import com.heitor.projeto.domain.dto.UsuarioDTO;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
	
	UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);
	
	Usuario toModel(UsuarioDTO usuarioDTO);
	
	UsuarioDTO toDto(Usuario usuario);

}
