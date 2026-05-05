package com.usco.plantilla_parcial_web.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usco.plantilla_parcial_web.entity.TipoVehiculo;
import com.usco.plantilla_parcial_web.service.TipoVehiculoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/tipos-vehiculo")
@Tag(name = "Tipos de vehículo", description = "Servicios REST para consultar los tipos de vehículo disponibles.")
public class TipoVehiculoRestController {

	private final TipoVehiculoService tipoVehiculoService;

	public TipoVehiculoRestController(TipoVehiculoService tipoVehiculoService) {
		this.tipoVehiculoService = tipoVehiculoService;
	}

	@GetMapping
	@Operation(summary = "Listar tipos de vehículo", description = "Retorna todos los tipos de vehículo registrados en la base de datos.")
	@ApiResponse(responseCode = "200", description = "Listado consultado correctamente")
	public ResponseEntity<List<TipoVehiculo>> listar() {
		return ResponseEntity.ok(tipoVehiculoService.listarTodos());
	}

	@GetMapping("/{id}")
	@Operation(summary = "Consultar tipo de vehículo por ID", description = "Busca un tipo de vehículo específico por su identificador.")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Tipo de vehículo encontrado"),
			@ApiResponse(responseCode = "404", description = "No existe un tipo de vehículo con ese ID", content = @Content)
	})
	public ResponseEntity<TipoVehiculo> buscarPorId(
			@Parameter(description = "ID del tipo de vehículo", example = "1", required = true) @PathVariable Long id) {
		try {
			return ResponseEntity.ok(tipoVehiculoService.buscarPorId(id));
		} catch (NoSuchElementException ex) {
			return ResponseEntity.notFound().build();
		}
	}
}
