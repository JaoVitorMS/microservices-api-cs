package org.zsh.matricula_service.client.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public record CursoDisponivelResponse(
        @JsonAlias({"disciplina", "nome"}) String disciplina,
        String horario
) {
}

