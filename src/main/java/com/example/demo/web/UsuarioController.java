package com.example.demo.web;

import com.example.demo.repository.UsuarioRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UsuarioController {
    private final UsuarioRepository usuarios;
    public UsuarioController(UsuarioRepository usuarios) { this.usuarios = usuarios; }
    @GetMapping("/admin/pacientes") @Operation(summary = "Lista los pacientes disponibles")
    public Object pacientes() { return usuarios.findAll().stream()
        .filter(u -> u.getRol().name().equals("PACIENTE"))
        .map(u -> java.util.Map.of("id", u.getId(), "username", u.getUsername(), "nombre", u.getNombre()))
        .toList(); }
    @GetMapping("/me") public Object me(Authentication a) { return usuarios.findByUsername(a.getName()).orElseThrow(); }
}
