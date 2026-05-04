package com.usco.plantilla_parcial_web.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.usco.plantilla_parcial_web.entity.Vehiculo;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {

	boolean existsByPlaca(String placa);

	@Override
	@EntityGraph(attributePaths = "tipoVehiculo")
	List<Vehiculo> findAll();

	@Override
	@EntityGraph(attributePaths = "tipoVehiculo")
	Optional<Vehiculo> findById(Long id);
}
