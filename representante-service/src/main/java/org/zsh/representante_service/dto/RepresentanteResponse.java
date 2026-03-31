package org.zsh.representante_service.dto;

public record RepresentanteResponse(
        Long id,
        String cpf,
        String nome
) {
}
