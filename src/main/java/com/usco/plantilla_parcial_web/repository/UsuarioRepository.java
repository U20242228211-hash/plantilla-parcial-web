package com.usco.plantilla_parcial_web.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usco.plantilla_parcial_web.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Optional<Usuario> findByUsername(String username);

	boolean existsByUsername(String username);
}
