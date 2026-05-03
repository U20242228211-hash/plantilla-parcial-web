package com.usco.plantilla_parcial_web.config;

import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				// Define que rutas puede consultar cada rol.
				.authorizeHttpRequests(auth -> auth
						.dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.ERROR)
						.permitAll()
						.requestMatchers("/login", "/procesar-login", "/acceso-denegado", "/css/**", "/js/**",
								"/images/**", "/swagger-ui/**", "/swagger-ui.html", "/v3/api-docs/**")
						.permitAll()
						.requestMatchers("/vehiculos/nuevo", "/vehiculos/guardar", "/vehiculos/editar/**",
								"/vehiculos/actualizar/**", "/vehiculos/eliminar/**")
						.hasAuthority("ROLE_ADMINISTRADOR")
						.requestMatchers("/vehiculos/ubicacion/**")
						.hasAnyAuthority("ROLE_ADMINISTRADOR", "ROLE_ACOMODADOR")
						.requestMatchers("/vehiculos/**")
						.hasAnyAuthority("ROLE_ADMINISTRADOR", "ROLE_ACOMODADOR", "ROLE_CLIENTE")
						.requestMatchers("/admin/**")
						.hasAuthority("ROLE_ADMINISTRADOR")
						.anyRequest()
						.authenticated())
				// Usa una pagina JSP propia para iniciar sesion.
				.formLogin(form -> form
						.loginPage("/login")
						.loginProcessingUrl("/procesar-login")
						.defaultSuccessUrl("/", true)
						.failureUrl("/login?error=true")
						.permitAll())
				// Cierra la sesion y vuelve al formulario de login.
				.logout(logout -> logout
						.logoutUrl("/logout")
						.logoutSuccessUrl("/login?logout=true")
						.invalidateHttpSession(true)
						.deleteCookies("JSESSIONID")
						.permitAll())
				// Muestra una pagina propia cuando el usuario no tiene permiso.
				.exceptionHandling(exception -> exception
						.accessDeniedPage("/acceso-denegado"));

		return http.build();
	}
}
