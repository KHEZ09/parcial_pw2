package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "consultas")
public class Consulta {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 40)
    private String pacienteNombre;
    @Column(nullable = false, length = 100)
    private String motivo;
    @Column(nullable = false)
    private Integer numeroConsultorio;
    @Column(nullable = false)
    private LocalDateTime horaInicio;
    @Column(nullable = false)
    private LocalDateTime horaFin;
    @Column(nullable = false, length = 50)
    private String pacienteUsername;
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Medico medico;

    public Consulta() {}
    public Long getId() { return id; }
    public String getPacienteNombre() { return pacienteNombre; }
    public void setPacienteNombre(String v) { pacienteNombre = v; }
    public String getMotivo() { return motivo; }
    public void setMotivo(String v) { motivo = v; }
    public Integer getNumeroConsultorio() { return numeroConsultorio; }
    public void setNumeroConsultorio(Integer v) { numeroConsultorio = v; }
    public LocalDateTime getHoraInicio() { return horaInicio; }
    public void setHoraInicio(LocalDateTime v) { horaInicio = v; }
    public LocalDateTime getHoraFin() { return horaFin; }
    public void setHoraFin(LocalDateTime v) { horaFin = v; }
    public String getPacienteUsername() { return pacienteUsername; }
    public void setPacienteUsername(String v) { pacienteUsername = v; }
    public Medico getMedico() { return medico; }
    public void setMedico(Medico v) { medico = v; }
}
