package org.zsh.matricula_service.client.dto;

public record EstudanteResponse(
        Long id,
        String nome,
        Long matriculaId
) {
}

