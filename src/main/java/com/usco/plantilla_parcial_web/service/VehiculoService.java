package com.usco.plantilla_parcial_web.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.usco.plantilla_parcial_web.entity.Vehiculo;
import com.usco.plantilla_parcial_web.repository.VehiculoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VehiculoService {

	private final VehiculoRepository vehiculoRepository;

	@Transactional(readOnly = true)
	public List<Vehiculo> listarTodos() {
		return vehiculoRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Vehiculo buscarPorId(Long id) {
		return vehiculoRepository.findById(id).orElseThrow();
	}

	@Transactional
	public Vehiculo guardar(Vehiculo vehiculo) {
		return vehiculoRepository.save(vehiculo);
	}

	@Transactional
	public void eliminar(Long id) {
		vehiculoRepository.deleteById(id);
	}

	@Transactional
	public void actualizarUbicacion(Long id, String ubicacion) {
		Vehiculo vehiculo = buscarPorId(id);
		vehiculo.setUbicacion(ubicacion);
		vehiculoRepository.save(vehiculo);
	}
}
