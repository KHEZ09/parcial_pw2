package com.example.demo.config;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {
    @Bean CommandLineRunner seed(UsuarioRepository usuarios, MedicoRepository medicos, PasswordEncoder encoder) {
        return args -> {
            guardarUsuario(usuarios, encoder, "admin", "admin123", "Administrador", Rol.ADMINISTRADOR);
            guardarUsuario(usuarios, encoder, "medico1", "medico123", "Dra. Ana Pérez", Rol.MEDICO);
            guardarUsuario(usuarios, encoder, "paciente1", "paciente123", "Paciente de Prueba", Rol.PACIENTE);
            if (medicos.count() == 0) medicos.save(new Medico("Dra. Ana Pérez", "Medicina general", "medico1"));
        };
    }

    private void guardarUsuario(UsuarioRepository usuarios, PasswordEncoder encoder, String username,
                                String password, String nombre, Rol rol) {
        var usuario = usuarios.findByUsername(username).orElseGet(Usuario::new);
        usuario.setUsername(username);
        usuario.setPassword(encoder.encode(password));
        usuario.setNombre(nombre);
        usuario.setRol(rol);
        usuarios.save(usuario);
    }
}
