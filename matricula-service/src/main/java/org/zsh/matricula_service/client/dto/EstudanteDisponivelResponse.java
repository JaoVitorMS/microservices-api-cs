package org.zsh.matricula_service.client.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public record EstudanteDisponivelResponse(
        @JsonAlias({"nome", "aluno"}) String nome
) {
}

