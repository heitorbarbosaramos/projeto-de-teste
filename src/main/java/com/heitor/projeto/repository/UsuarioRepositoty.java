package com.heitor.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.heitor.projeto.domain.Usuario;

@Repository
public interface UsuarioRepositoty extends JpaRepository<Usuario, Long> {

}
