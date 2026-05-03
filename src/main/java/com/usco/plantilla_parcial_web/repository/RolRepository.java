package com.usco.plantilla_parcial_web.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usco.plantilla_parcial_web.entity.Rol;

public interface RolRepository extends JpaRepository<Rol, Long> {

	boolean existsByNombre(String nombre);

	Optional<Rol> findByNombre(String nombre);
}
