-- =========================================================
-- Sistema de Gestion de Parqueadero
-- Script de referencia para entrega del parcial
-- =========================================================

CREATE DATABASE IF NOT EXISTS plantilla_parcial_web;

USE plantilla_parcial_web;

-- Las tablas del proyecto pueden ser generadas automaticamente por JPA
-- porque en application.properties esta configurado:
--
-- spring.jpa.hibernate.ddl-auto=update
--
-- Por esa razon, este archivo se deja como referencia documental para
-- mostrar la base de datos y los datos iniciales esperados.
--
-- La aplicacion carga automaticamente estos datos desde la clase
-- DataInitializer cuando no existen.

-- =========================================================
-- Roles de referencia
-- =========================================================

INSERT INTO rol (id, nombre) VALUES
(1, 'ROLE_ADMINISTRADOR'),
(2, 'ROLE_ACOMODADOR'),
(3, 'ROLE_CLIENTE');

-- =========================================================
-- Tipos de vehiculo de referencia
-- =========================================================

INSERT INTO tipo_vehiculo (id, nombre) VALUES
(1, 'Carro'),
(2, 'Moto'),
(3, 'Camioneta');

-- =========================================================
-- Usuarios de prueba
-- =========================================================
--
-- Usuarios usados por la aplicacion:
-- admin / admin123 / ROLE_ADMINISTRADOR
-- acomodador / acomodador123 / ROLE_ACOMODADOR
-- cliente / cliente123 / ROLE_CLIENTE
--
-- Importante:
-- Las contrasenas reales no se guardan en texto plano.
-- DataInitializer las carga cifradas con BCrypt usando BCryptPasswordEncoder.
--
-- Los hashes siguientes son de referencia para explicar la estructura
-- esperada de la tabla usuario.

INSERT INTO usuario (id, username, password, enabled, rol_id) VALUES
(1, 'admin', '$2a$10$7eqJtq98hPqEX7fNZaFWoOhiV7esJZdC5hqnEpBNv8o/olJiGJ41K', true, 1),
(2, 'acomodador', '$2a$10$Wn/XnCPXFg/d5JuyGM0OheRq9G5qyWEwv98WkSeXChE3Q9b0sbH6e', true, 2),
(3, 'cliente', '$2a$10$q0NrcaNlqj2gk6ZJaaEyE.2v4Qcz3p4U5pXfpkvtY3nUX6qV3xP7S', true, 3);

-- =========================================================
-- Vehiculos de prueba
-- =========================================================
--
-- Las horas usan formato HH:mm.
-- hora_salida puede quedar en NULL cuando el vehiculo no ha salido.

INSERT INTO vehiculo (id, placa, hora_entrada, hora_salida, ubicacion, tipo_vehiculo_id) VALUES
(1, 'ABC123', '08:00', NULL, 'A-1', 1),
(2, 'XYZ789', '09:00', NULL, 'B-2', 2),
(3, 'KLM456', '10:00', '12:00', 'C-3', 3);
