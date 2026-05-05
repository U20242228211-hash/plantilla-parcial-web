package com.usco.plantilla_parcial_web.entity;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Vehiculo {

	private static final DateTimeFormatter FORMATO_HORA = DateTimeFormatter.ofPattern("HH:mm");

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 6)
	@Column(nullable = false, length = 6)
	private String placa;

	@NotNull
	@DateTimeFormat(pattern = "HH:mm")
	@Column(nullable = false)
	private LocalTime horaEntrada;

	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime horaSalida;

	@NotBlank
	@Column(nullable = false)
	private String ubicacion;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tipo_vehiculo_id", nullable = false)
	private TipoVehiculo tipoVehiculo;

	public void setHoraEntrada(LocalTime horaEntrada) {
		this.horaEntrada = limpiarSegundos(horaEntrada);
	}

	public void setHoraSalida(LocalTime horaSalida) {
		this.horaSalida = limpiarSegundos(horaSalida);
	}

	@Transient
	public String getHoraEntradaFormato() {
		return formatearHora(horaEntrada);
	}

	@Transient
	public String getHoraSalidaFormato() {
		return formatearHora(horaSalida);
	}

	private LocalTime limpiarSegundos(LocalTime hora) {
		return hora == null ? null : hora.withSecond(0).withNano(0);
	}

	private String formatearHora(LocalTime hora) {
		return hora == null ? null : hora.format(FORMATO_HORA);
	}
}
