package org.zsh.representante_service.dto;

import jakarta.validation.constraints.NotBlank;

public record RepresentanteDto(
        @NotBlank String cpf,
        @NotBlank String nome
) {
}
