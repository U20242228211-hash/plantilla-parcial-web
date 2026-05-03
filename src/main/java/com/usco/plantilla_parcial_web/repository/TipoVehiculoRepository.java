package com.usco.plantilla_parcial_web.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usco.plantilla_parcial_web.entity.TipoVehiculo;

public interface TipoVehiculoRepository extends JpaRepository<TipoVehiculo, Long> {

	boolean existsByNombre(String nombre);

	Optional<TipoVehiculo> findByNombre(String nombre);
}
