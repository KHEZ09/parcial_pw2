package com.example.demo.web;

import com.example.demo.model.Consulta;
import com.example.demo.repository.*;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "Consultas médicas")
public class ConsultaController {
    private final ConsultaRepository consultas; private final MedicoRepository medicos;
    public ConsultaController(ConsultaRepository c, MedicoRepository m) { consultas = c; medicos = m; }

    @GetMapping("/admin/consultas")
    @Operation(summary = "Lista todas las consultas")
    @ApiResponse(responseCode = "200", description = "Consultas encontradas")
    @ApiResponse(responseCode = "401", description = "No autenticado")
    @ApiResponse(responseCode = "403", description = "Sin permisos")
    public List<Consulta> todas() { return consultas.findAll(); }

    @PostMapping("/admin/consultas")
    @Operation(summary = "Crea una consulta y asigna médico y paciente")
    public ResponseEntity<Consulta> crear(@Valid @RequestBody ConsultaRequest r) {
        var c = new Consulta(); copiar(c, r); return ResponseEntity.status(HttpStatus.CREATED).body(consultas.save(c));
    }

    @PutMapping("/admin/consultas/{id}")
    @Operation(summary = "Edita una consulta")
    public Consulta editar(@PathVariable Long id, @Valid @RequestBody ConsultaRequest r) {
        var c = consultas.findById(id).orElseThrow(); copiar(c, r); return consultas.save(c);
    }

    @DeleteMapping("/admin/consultas/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Elimina una consulta")
    public void eliminar(@PathVariable Long id) { consultas.deleteById(id); }

    @GetMapping("/admin/medicos") public Object medicos() { return medicos.findAll(); }

    @PostMapping("/admin/medicos")
    @Operation(summary = "Crea un médico")
    public ResponseEntity<com.example.demo.model.Medico> crearMedico(@Valid @RequestBody MedicoRequest r) {
        return ResponseEntity.status(HttpStatus.CREATED).body(medicos.save(new com.example.demo.model.Medico(r.nombre(), r.especialidad(), r.username())));
    }

    @PutMapping("/admin/medicos/{id}")
    @Operation(summary = "Edita un médico")
    public com.example.demo.model.Medico editarMedico(@PathVariable Long id, @Valid @RequestBody MedicoRequest r) {
        var m = medicos.findById(id).orElseThrow(); m.setNombre(r.nombre()); m.setEspecialidad(r.especialidad()); m.setUsername(r.username()); return medicos.save(m);
    }

    @DeleteMapping("/admin/medicos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Elimina un médico")
    public void eliminarMedico(@PathVariable Long id) { medicos.deleteById(id); }

    @GetMapping("/medico/consultas")
    @Operation(summary = "Consultas asignadas al médico autenticado")
    public List<Consulta> delMedico(Authentication a) { return consultas.findByMedicoUsername(a.getName()); }

    @GetMapping("/paciente/consultas")
    @Operation(summary = "Consultas del paciente autenticado")
    public List<Consulta> delPaciente(Authentication a) { return consultas.findByPacienteUsername(a.getName()); }

    @PatchMapping("/paciente/consultas/{id}/horario")
    @Operation(summary = "Actualiza horario de una cita asignada")
    public Consulta horario(@PathVariable Long id, @Valid @RequestBody HorarioRequest r, Authentication a) {
        var c = consultas.findById(id).orElseThrow();
        if (!a.getName().equals(c.getPacienteUsername())) throw new org.springframework.security.access.AccessDeniedException("La cita no está asignada al paciente");
        c.setHoraInicio(r.horaInicio()); c.setHoraFin(r.horaFin()); return consultas.save(c);
    }

    private void copiar(Consulta c, ConsultaRequest r) {
        c.setPacienteNombre(r.pacienteNombre()); c.setMotivo(r.motivo()); c.setNumeroConsultorio(r.numeroConsultorio());
        c.setHoraInicio(r.horaInicio()); c.setHoraFin(r.horaFin()); c.setPacienteUsername(r.pacienteUsername());
        c.setMedico(medicos.findById(r.medicoId()).orElseThrow());
    }
}
