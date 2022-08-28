package com.heitor.projeto.builder;

import com.heitor.projeto.domain.Usuario;
import com.heitor.projeto.domain.dto.UsuarioDTO;

public class UsuarioBuilder {
	
	public final static Long ID 				= 1l;
	public final static String NOME 			= "Usuario Nome de Teste";
	public final static String EMAIL 			= "email@email.com";
	public final static String SENHA 			= "123";
	public final static String NOME_ATUALIZA 	= "Usuario Novo Nome Atualizado";

	public static Usuario criarObjeto() {
		
		Usuario usuario = new Usuario();
		usuario.setId(ID);
		usuario.setEmail(EMAIL);
		usuario.setName(NOME);
		usuario.setPassword(SENHA);
		
		return usuario;
	}
	
	public static UsuarioDTO criarObjetDto() {
		
		UsuarioDTO dto = new UsuarioDTO();
		dto.setId(ID);
		dto.setEmail(EMAIL);
		dto.setName(NOME);
		dto.setPassword(SENHA);
		
		return dto;
	}
}
