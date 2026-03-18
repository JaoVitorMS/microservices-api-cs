package org.zsh.matricula_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.zsh.matricula_service.client.dto.EstudanteDisponivelResponse;
import org.zsh.matricula_service.client.dto.EstudanteElegibilidadeResponse;
import org.zsh.matricula_service.client.dto.EstudanteResponse;

import java.util.List;

@FeignClient(
		name = "estudante-service"
)
public interface EstudanteClient {


	@GetMapping("/estudantes/consultarPorNome/{nome}")
	EstudanteResponse buscarPorNome( @PathVariable String nome);

}
