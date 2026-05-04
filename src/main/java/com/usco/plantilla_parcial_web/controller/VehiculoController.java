package com.usco.plantilla_parcial_web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.usco.plantilla_parcial_web.entity.TipoVehiculo;
import com.usco.plantilla_parcial_web.entity.Vehiculo;
import com.usco.plantilla_parcial_web.service.TipoVehiculoService;
import com.usco.plantilla_parcial_web.service.VehiculoService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/vehiculos")
@RequiredArgsConstructor
public class VehiculoController {

	private final VehiculoService vehiculoService;
	private final TipoVehiculoService tipoVehiculoService;

	@GetMapping
	public String listar(Model model) {
		model.addAttribute("vehiculos", vehiculoService.listarTodos());
		return "vehiculos/listar";
	}

	@GetMapping("/nuevo")
	public String nuevo(Model model) {
		model.addAttribute("vehiculo", new Vehiculo());
		model.addAttribute("tiposVehiculo", tipoVehiculoService.listarTodos());
		model.addAttribute("accion", "/vehiculos/guardar");
		model.addAttribute("titulo", "Nuevo vehículo");
		return "vehiculos/formulario";
	}

	@PostMapping("/guardar")
	public String guardar(@ModelAttribute Vehiculo vehiculo, @RequestParam Long tipoVehiculoId) {
		TipoVehiculo tipoVehiculo = tipoVehiculoService.buscarPorId(tipoVehiculoId);
		vehiculo.setTipoVehiculo(tipoVehiculo);
		vehiculoService.guardar(vehiculo);
		return "redirect:/vehiculos";
	}

	@GetMapping("/ver/{id}")
	public String ver(@PathVariable Long id, Model model) {
		model.addAttribute("vehiculo", vehiculoService.buscarPorId(id));
		return "vehiculos/ver";
	}

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable Long id, Model model) {
		model.addAttribute("vehiculo", vehiculoService.buscarPorId(id));
		model.addAttribute("tiposVehiculo", tipoVehiculoService.listarTodos());
		model.addAttribute("accion", "/vehiculos/actualizar/" + id);
		model.addAttribute("titulo", "Editar vehículo");
		return "vehiculos/formulario";
	}

	@PostMapping("/actualizar/{id}")
	public String actualizar(@PathVariable Long id, @ModelAttribute Vehiculo datosFormulario,
			@RequestParam Long tipoVehiculoId) {
		Vehiculo vehiculo = vehiculoService.buscarPorId(id);
		TipoVehiculo tipoVehiculo = tipoVehiculoService.buscarPorId(tipoVehiculoId);

		vehiculo.setPlaca(datosFormulario.getPlaca());
		vehiculo.setHoraEntrada(datosFormulario.getHoraEntrada());
		vehiculo.setHoraSalida(datosFormulario.getHoraSalida());
		vehiculo.setUbicacion(datosFormulario.getUbicacion());
		vehiculo.setTipoVehiculo(tipoVehiculo);

		vehiculoService.guardar(vehiculo);
		return "redirect:/vehiculos";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Long id) {
		vehiculoService.eliminar(id);
		return "redirect:/vehiculos";
	}

	@GetMapping("/ubicacion/{id}")
	public String editarUbicacion(@PathVariable Long id, Model model) {
		model.addAttribute("vehiculo", vehiculoService.buscarPorId(id));
		return "vehiculos/editar-ubicacion";
	}

	@PostMapping("/ubicacion/{id}")
	public String actualizarUbicacion(@PathVariable Long id, @RequestParam String ubicacion) {
		vehiculoService.actualizarUbicacion(id, ubicacion);
		return "redirect:/vehiculos";
	}
}
