package com.usco.plantilla_parcial_web.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.usco.plantilla_parcial_web.entity.TipoVehiculo;
import com.usco.plantilla_parcial_web.repository.TipoVehiculoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TipoVehiculoService {

	private final TipoVehiculoRepository tipoVehiculoRepository;

	@Transactional(readOnly = true)
	public List<TipoVehiculo> listarTodos() {
		return tipoVehiculoRepository.findAll();
	}

	@Transactional(readOnly = true)
	public TipoVehiculo buscarPorId(Long id) {
		return tipoVehiculoRepository.findById(id).orElseThrow();
	}
}
