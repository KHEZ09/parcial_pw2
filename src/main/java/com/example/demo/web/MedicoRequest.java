package com.example.demo.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MedicoRequest(@NotBlank @Size(max = 80) String nombre,
                            @NotBlank @Size(max = 80) String especialidad,
                            @NotBlank @Size(max = 50) String username) {}
