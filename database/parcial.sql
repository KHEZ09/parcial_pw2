-- Script de apoyo para PostgreSQL. Ejecutar conectado a la base de datos parcial.
-- La aplicación también puede crear estas tablas con JPA (ddl-auto=update).
CREATE TABLE IF NOT EXISTS usuarios (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    nombre VARCHAR(80) NOT NULL,
    rol VARCHAR(20) NOT NULL CHECK (rol IN ('ADMINISTRADOR','MEDICO','PACIENTE'))
);
CREATE TABLE IF NOT EXISTS medicos (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(80) NOT NULL,
    especialidad VARCHAR(80) NOT NULL,
    username VARCHAR(50) NOT NULL UNIQUE
);
CREATE TABLE IF NOT EXISTS consultas (
    id BIGSERIAL PRIMARY KEY,
    paciente_nombre VARCHAR(40) NOT NULL,
    motivo VARCHAR(100) NOT NULL,
    numero_consultorio INTEGER NOT NULL,
    hora_inicio TIMESTAMP NOT NULL,
    hora_fin TIMESTAMP NOT NULL,
    paciente_username VARCHAR(50) NOT NULL,
    medico_id BIGINT NOT NULL REFERENCES medicos(id)
);
-- Las contraseñas iniciales se generan con BCrypt desde DataInitializer.
