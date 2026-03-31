package org.zsh.peca_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PecaDto(
        @NotNull Long id,
        @NotBlank String nome,
        @NotBlank String descricao
) {
}
