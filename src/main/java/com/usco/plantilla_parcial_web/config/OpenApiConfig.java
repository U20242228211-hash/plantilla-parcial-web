package com.usco.plantilla_parcial_web.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(info = @Info(
		title = "Plantilla Parcial Web API",
		version = "1.0",
		description = "Servicios REST para probar el CRUD de vehículos y tipos de vehículo desde Swagger UI."))
public class OpenApiConfig {
}
