package com.example.demo.web;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record HorarioRequest(@NotNull LocalDateTime horaInicio, @NotNull LocalDateTime horaFin) {}
