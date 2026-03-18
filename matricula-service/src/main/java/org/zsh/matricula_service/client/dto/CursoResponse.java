package org.zsh.matricula_service.client.dto;

public record CursoResponse(
        Long id,
        String nome,
        String codigo,
        String horario
) {
}

