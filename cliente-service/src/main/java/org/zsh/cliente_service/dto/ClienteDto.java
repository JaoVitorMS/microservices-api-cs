package org.zsh.cliente_service.dto;

import jakarta.validation.constraints.NotBlank;

public record ClienteDto(
        @NotBlank String cpf,
        @NotBlank String nome
) {
}
