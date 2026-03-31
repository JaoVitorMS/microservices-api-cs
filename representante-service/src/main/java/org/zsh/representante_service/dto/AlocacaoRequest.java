package org.zsh.representante_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AlocacaoRequest(
        @NotBlank String cpfRepresentante,
        @NotBlank String cpfCliente,
        @NotNull Long idPeca
) {
}
