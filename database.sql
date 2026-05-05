CREATE DATABASE IF NOT EXISTS plantilla_parcial_web;
USE plantilla_parcial_web;

-- Los datos iniciales se insertan automaticamente al arrancar la aplicacion
-- mediante la clase DataInitializer.
--
-- Roles:
-- ROLE_ADMINISTRADOR, ROLE_ACOMODADOR, ROLE_CLIENTE
--
-- Usuarios de referencia:
-- admin / admin123 / ROLE_ADMINISTRADOR
-- acomodador / acomodador123 / ROLE_ACOMODADOR
-- cliente / cliente123 / ROLE_CLIENTE
--
-- Tipos de vehiculo:
-- Carro, Moto, Camioneta
--
-- Vehiculos de prueba:
-- ABC123 / 08:00 / null / A-1 / Carro
-- XYZ789 / 09:00 / null / B-2 / Moto
-- KLM456 / 10:00 / 12:00 / C-3 / Camioneta
