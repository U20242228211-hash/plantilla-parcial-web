package com.usco.plantilla_parcial_web.config;

import java.time.LocalTime;

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
		crearOActualizarVehiculoInicial("ABC123", LocalTime.of(8, 0), null, "A-1", "Carro");
		crearOActualizarVehiculoInicial("XYZ789", LocalTime.of(9, 0), null, "B-2", "Moto");
		crearOActualizarVehiculoInicial("KLM456", LocalTime.of(10, 0), LocalTime.of(12, 0), "C-3", "Camioneta");
		corregirHorasGuardadasComoSegundos();
	}

	private void crearOActualizarVehiculoInicial(String placa, LocalTime horaEntrada, LocalTime horaSalida, String ubicacion,
			String nombreTipoVehiculo) {
		TipoVehiculo tipoVehiculo = tipoVehiculoRepository.findByNombre(nombreTipoVehiculo).orElseThrow();
		Vehiculo vehiculo = vehiculoRepository.findByPlaca(placa)
				.orElse(new Vehiculo(null, placa, horaEntrada, horaSalida, ubicacion, tipoVehiculo));

		vehiculo.setHoraEntrada(horaEntrada);
		vehiculo.setHoraSalida(horaSalida);
		vehiculo.setUbicacion(ubicacion);
		vehiculo.setTipoVehiculo(tipoVehiculo);
		vehiculoRepository.save(vehiculo);
	}

	private void corregirHorasGuardadasComoSegundos() {
		vehiculoRepository.findAll().forEach(vehiculo -> {
			vehiculo.setHoraEntrada(corregirHora(vehiculo.getHoraEntrada()));
			vehiculo.setHoraSalida(corregirHora(vehiculo.getHoraSalida()));
			vehiculoRepository.save(vehiculo);
		});
	}

	private LocalTime corregirHora(LocalTime hora) {
		if (hora == null) {
			return null;
		}

		if (hora.getHour() == 0 && hora.getMinute() == 0 && hora.getSecond() >= 1 && hora.getSecond() <= 23) {
			return LocalTime.of(hora.getSecond(), 0);
		}

		return hora.withSecond(0).withNano(0);
	}
}
