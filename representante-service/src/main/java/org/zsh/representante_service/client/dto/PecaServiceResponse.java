package org.zsh.representante_service.client.dto;

public record PecaServiceResponse(
        Long id,
        String nome,
        String descricao
) {
}
