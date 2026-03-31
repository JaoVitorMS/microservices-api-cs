package org.zsh.representante_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.zsh.representante_service.client.dto.PecaServiceResponse;

@FeignClient(name = "peca-service")
public interface PecaClient {

    @GetMapping("/pecas/{id}")
    PecaServiceResponse buscarPorId(@PathVariable("id") Long id);
}
