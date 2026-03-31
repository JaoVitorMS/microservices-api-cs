package org.zsh.peca_service.dto;

public record PecaResponse(
        Long id,
        String nome,
        String descricao
) {
}
