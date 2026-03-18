package org.zsh.matricula_service.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MatricularRequest(
        @NotBlank String disciplina,
        @NotBlank String horario,
        @NotBlank String nomeAluno
) {
}

