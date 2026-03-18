package org.zsh.matricula_service.client.dto;

public record CursoDisponibilidadeResponse(
        boolean disponivel,
        String motivo
) {
}

