package org.zsh.cliente_service.dto;

public record ClienteResponse(
        Long id,
        String cpf,
        String nome
) {
}
