package com.usco.plantilla_parcial_web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usco.plantilla_parcial_web.entity.Vehiculo;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {

	boolean existsByPlaca(String placa);
}
