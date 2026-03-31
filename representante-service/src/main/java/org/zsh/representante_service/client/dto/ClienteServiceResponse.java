package org.zsh.representante_service.client.dto;

public record ClienteServiceResponse(
        Long id,
        String cpf,
        String nome
) {
}
