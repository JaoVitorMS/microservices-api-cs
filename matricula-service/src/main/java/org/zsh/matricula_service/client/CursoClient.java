package org.zsh.matricula_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.zsh.matricula_service.client.dto.CursoDisponivelResponse;
import org.zsh.matricula_service.client.dto.CursoDisponibilidadeResponse;
import org.zsh.matricula_service.client.dto.CursoResponse;

import java.util.List;

@FeignClient(
        name = "curso-service"
)
public interface CursoClient {

    @GetMapping("/cursos/encontrar-todos")
    List<CursoDisponivelResponse> listarDisponiveis();

}
