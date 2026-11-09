package com.example.demo.web;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public record ConsultaRequest(
    @NotBlank @Size(max = 40) String pacienteNombre,
    @NotBlank @Size(max = 100) String motivo,
    @NotNull @Positive Integer numeroConsultorio,
    @NotNull LocalDateTime horaInicio,
    @NotNull LocalDateTime horaFin,
    @NotBlank String pacienteUsername,
    @NotNull Long medicoId
) {}
