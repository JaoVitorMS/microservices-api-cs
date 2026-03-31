package org.zsh.cliente_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zsh.cliente_service.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

private final ClienteService clienteService;

public ClienteController(ClienteService clienteService) {
this.clienteService = clienteService;
}

@GetMapping("/boilerplate")
public String boilerplate() {
return clienteService.status();
}
}
