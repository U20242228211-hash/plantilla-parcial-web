package com.usco.plantilla_parcial_web.controller;

import java.time.LocalTime;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.usco.plantilla_parcial_web.entity.TipoVehiculo;
import com.usco.plantilla_parcial_web.entity.Vehiculo;
import com.usco.plantilla_parcial_web.service.TipoVehiculoService;
import com.usco.plantilla_parcial_web.service.VehiculoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@RestController
@RequestMapping("/api/vehiculos")
@Tag(name = "Vehículos", description = "Servicios REST para consultar y administrar vehículos del parqueadero.")
public class VehiculoRestController {

	private final VehiculoService vehiculoService;
	private final TipoVehiculoService tipoVehiculoService;

	public VehiculoRestController(VehiculoService vehiculoService, TipoVehiculoService tipoVehiculoService) {
		this.vehiculoService = vehiculoService;
		this.tipoVehiculoService = tipoVehiculoService;
	}

	@GetMapping
	@Operation(summary = "Listar vehículos", description = "Retorna todos los vehículos registrados con su tipo de vehículo.")
	@ApiResponse(responseCode = "200", description = "Listado consultado correctamente")
	public ResponseEntity<List<Vehiculo>> listar() {
		return ResponseEntity.ok(vehiculoService.listarTodos());
	}

	@GetMapping("/{id}")
	@Operation(summary = "Consultar vehículo por ID", description = "Busca un vehículo específico por su identificador.")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Vehículo encontrado"),
			@ApiResponse(responseCode = "404", description = "No existe un vehículo con ese ID", content = @Content)
	})
	public ResponseEntity<Vehiculo> buscarPorId(
			@Parameter(description = "ID del vehículo", example = "1", required = true) @PathVariable Long id) {
		try {
			return ResponseEntity.ok(vehiculoService.buscarPorId(id));
		} catch (NoSuchElementException ex) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	@Operation(summary = "Crear vehículo", description = "Crea un vehículo nuevo. Requiere rol ROLE_ADMINISTRADOR.")
	@io.swagger.v3.oas.annotations.parameters.RequestBody(
			required = true,
			description = "Datos del vehículo a crear. Las horas deben enviarse en formato HH:mm.",
			content = @Content(examples = @ExampleObject(value = """
					{
					  "placa": "DEF456",
					  "horaEntrada": "08:30",
					  "horaSalida": null,
					  "ubicacion": "D-4",
					  "tipoVehiculoId": 1
					}
					""")))
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Vehículo creado correctamente"),
			@ApiResponse(responseCode = "404", description = "No existe el tipo de vehículo indicado", content = @Content)
	})
	public ResponseEntity<Vehiculo> crear(@Valid @RequestBody VehiculoRequest request) {
		try {
			Vehiculo vehiculo = new Vehiculo();
			aplicarDatos(vehiculo, request);
			return ResponseEntity.ok(vehiculoService.guardar(vehiculo));
		} catch (NoSuchElementException ex) {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/{id}")
	@Operation(summary = "Actualizar vehículo", description = "Actualiza todos los datos editables de un vehículo existente. Requiere rol ROLE_ADMINISTRADOR.")
	@io.swagger.v3.oas.annotations.parameters.RequestBody(
			required = true,
			description = "Datos completos del vehículo. Las horas deben enviarse en formato HH:mm.",
			content = @Content(examples = @ExampleObject(value = """
					{
					  "placa": "DEF456",
					  "horaEntrada": "08:30",
					  "horaSalida": "17:45",
					  "ubicacion": "D-5",
					  "tipoVehiculoId": 2
					}
					""")))
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Vehículo actualizado correctamente"),
			@ApiResponse(responseCode = "404", description = "No existe el vehículo o el tipo de vehículo indicado", content = @Content)
	})
	public ResponseEntity<Vehiculo> actualizar(
			@Parameter(description = "ID del vehículo", example = "1", required = true) @PathVariable Long id,
			@Valid @RequestBody VehiculoRequest request) {
		try {
			Vehiculo vehiculo = vehiculoService.buscarPorId(id);
			aplicarDatos(vehiculo, request);
			return ResponseEntity.ok(vehiculoService.guardar(vehiculo));
		} catch (NoSuchElementException ex) {
			return ResponseEntity.notFound().build();
		}
	}

	@PatchMapping("/{id}/ubicacion")
	@Operation(summary = "Actualizar ubicación", description = "Actualiza únicamente la ubicación del vehículo. Requiere rol ROLE_ADMINISTRADOR o ROLE_ACOMODADOR.")
	@io.swagger.v3.oas.annotations.parameters.RequestBody(
			required = true,
			description = "Nueva ubicación del vehículo.",
			content = @Content(examples = @ExampleObject(value = """
					{
					  "ubicacion": "B-8"
					}
					""")))
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Ubicación actualizada correctamente"),
			@ApiResponse(responseCode = "404", description = "No existe un vehículo con ese ID", content = @Content)
	})
	public ResponseEntity<Vehiculo> actualizarUbicacion(
			@Parameter(description = "ID del vehículo", example = "1", required = true) @PathVariable Long id,
			@Valid @RequestBody UbicacionRequest request) {
		try {
			vehiculoService.actualizarUbicacion(id, request.getUbicacion());
			return ResponseEntity.ok(vehiculoService.buscarPorId(id));
		} catch (NoSuchElementException ex) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Eliminar vehículo", description = "Elimina un vehículo por ID. Requiere rol ROLE_ADMINISTRADOR.")
	@ApiResponses({
			@ApiResponse(responseCode = "204", description = "Vehículo eliminado correctamente"),
			@ApiResponse(responseCode = "404", description = "No existe un vehículo con ese ID", content = @Content)
	})
	public ResponseEntity<Void> eliminar(
			@Parameter(description = "ID del vehículo", example = "1", required = true) @PathVariable Long id) {
		try {
			vehiculoService.buscarPorId(id);
			vehiculoService.eliminar(id);
			return ResponseEntity.noContent().build();
		} catch (NoSuchElementException ex) {
			return ResponseEntity.notFound().build();
		}
	}

	private void aplicarDatos(Vehiculo vehiculo, VehiculoRequest request) {
		TipoVehiculo tipoVehiculo = tipoVehiculoService.buscarPorId(request.getTipoVehiculoId());
		vehiculo.setPlaca(request.getPlaca());
		vehiculo.setHoraEntrada(request.getHoraEntrada());
		vehiculo.setHoraSalida(request.getHoraSalida());
		vehiculo.setUbicacion(request.getUbicacion());
		vehiculo.setTipoVehiculo(tipoVehiculo);
	}

	@Data
	@Schema(description = "Datos necesarios para crear o actualizar un vehículo.")
	public static class VehiculoRequest {

		@NotBlank
		@Size(max = 6)
		@Schema(description = "Placa del vehículo. Máximo 6 caracteres.", example = "DEF456")
		private String placa;

		@NotNull
		@JsonFormat(pattern = "HH:mm")
		@Schema(description = "Hora de entrada en formato HH:mm.", example = "08:30", type = "string")
		private LocalTime horaEntrada;

		@JsonFormat(pattern = "HH:mm")
		@Schema(description = "Hora de salida en formato HH:mm. Puede omitirse o enviarse como null.", example = "17:45", type = "string")
		private LocalTime horaSalida;

		@NotBlank
		@Schema(description = "Ubicación asignada en el parqueadero.", example = "D-4")
		private String ubicacion;

		@NotNull
		@Schema(description = "ID del tipo de vehículo existente.", example = "1")
		private Long tipoVehiculoId;
	}

	@Data
	@Schema(description = "Datos para actualizar solo la ubicación de un vehículo.")
	public static class UbicacionRequest {

		@NotBlank
		@Schema(description = "Nueva ubicación del vehículo.", example = "B-8")
		private String ubicacion;
	}
}
