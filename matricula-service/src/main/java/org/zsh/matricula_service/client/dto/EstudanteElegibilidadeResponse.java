package org.zsh.matricula_service.client.dto;

public record EstudanteElegibilidadeResponse(
        boolean elegivel,
        String motivo
) {
}

