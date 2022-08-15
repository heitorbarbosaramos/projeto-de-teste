package com.heitor.projeto.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UsuarioDTO {
	
	private Long id;
	private String name;
	private String email;

}
