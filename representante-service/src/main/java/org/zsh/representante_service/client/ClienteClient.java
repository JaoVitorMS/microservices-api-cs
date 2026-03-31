package org.zsh.representante_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.zsh.representante_service.client.dto.ClienteServiceResponse;

@FeignClient(name = "cliente-service")
public interface ClienteClient {

    @GetMapping("/clientes/cpf/{cpf}")
    ClienteServiceResponse buscarPorCpf(@PathVariable("cpf") String cpf);
}
