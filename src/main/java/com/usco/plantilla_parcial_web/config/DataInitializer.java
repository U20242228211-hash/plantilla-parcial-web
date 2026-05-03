package com.usco.plantilla_parcial_web.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.usco.plantilla_parcial_web.entity.Rol;
import com.usco.plantilla_parcial_web.entity.TipoVehiculo;
import com.usco.plantilla_parcial_web.entity.Usuario;
import com.usco.plantilla_parcial_web.entity.Vehiculo;
import com.usco.plantilla_parcial_web.repository.RolRepository;
import com.usco.plantilla_parcial_web.repository.TipoVehiculoRepository;
import com.usco.plantilla_parcial_web.repository.UsuarioRepository;
import com.usco.plantilla_parcial_web.repository.VehiculoRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

	private final RolRepository rolRepository;
	private final UsuarioRepository usuarioRepository;
	private final TipoVehiculoRepository tipoVehiculoRepository;
	private final VehiculoRepository vehiculoRepository;

	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Override
	public void run(String... args) {
		crearRoles();
		crearUsuarios();
		crearTiposVehiculo();
		crearVehiculos();
	}

	private void crearRoles() {
		crearRolSiNoExiste("ROLE_ADMINISTRADOR");
		crearRolSiNoExiste("ROLE_ACOMODADOR");
		crearRolSiNoExiste("ROLE_CLIENTE");
	}

	private void crearRolSiNoExiste(String nombre) {
		if (!rolRepository.existsByNombre(nombre)) {
			rolRepository.save(new Rol(null, nombre));
		}
	}

	private void crearUsuarios() {
		crearUsuarioSiNoExiste("admin", "admin123", "ROLE_ADMINISTRADOR");
		crearUsuarioSiNoExiste("acomodador", "acomodador123", "ROLE_ACOMODADOR");
		crearUsuarioSiNoExiste("cliente", "cliente123", "ROLE_CLIENTE");
	}

	private void crearUsuarioSiNoExiste(String username, String password, String nombreRol) {
		if (!usuarioRepository.existsByUsername(username)) {
			Rol rol = rolRepository.findByNombre(nombreRol).orElseThrow();
			usuarioRepository.save(new Usuario(null, username, passwordEncoder.encode(password), true, rol));
		}
	}

	private void crearTiposVehiculo() {
		crearTipoVehiculoSiNoExiste("Carro");
		crearTipoVehiculoSiNoExiste("Moto");
		crearTipoVehiculoSiNoExiste("Camioneta");
	}

	private void crearTipoVehiculoSiNoExiste(String nombre) {
		if (!tipoVehiculoRepository.existsByNombre(nombre)) {
			tipoVehiculoRepository.save(new TipoVehiculo(null, nombre));
		}
	}

	private void crearVehiculos() {
		crearVehiculoSiNoExiste("ABC123", 8, null, "A-1", "Carro");
		crearVehiculoSiNoExiste("XYZ789", 9, null, "B-2", "Moto");
		crearVehiculoSiNoExiste("KLM456", 10, 12, "C-3", "Camioneta");
	}

	private void crearVehiculoSiNoExiste(String placa, Integer horaEntrada, Integer horaSalida, String ubicacion,
			String nombreTipoVehiculo) {
		if (!vehiculoRepository.existsByPlaca(placa)) {
			TipoVehiculo tipoVehiculo = tipoVehiculoRepository.findByNombre(nombreTipoVehiculo).orElseThrow();
			vehiculoRepository.save(new Vehiculo(null, placa, horaEntrada, horaSalida, ubicacion, tipoVehiculo));
		}
	}
}
