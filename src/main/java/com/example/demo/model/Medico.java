package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "medicos")
public class Medico {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 80)
    private String nombre;
    @Column(nullable = false, length = 80)
    private String especialidad;
    @Column(nullable = false, unique = true, length = 50)
    private String username;

    public Medico() {}
    public Medico(String nombre, String especialidad, String username) {
        this.nombre = nombre; this.especialidad = especialidad; this.username = username;
    }
    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
}
