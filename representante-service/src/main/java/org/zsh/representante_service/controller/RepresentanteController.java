package org.zsh.representante_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zsh.representante_service.service.RepresentanteService;

@RestController
@RequestMapping("/representantes")
public class RepresentanteController {

private final RepresentanteService representanteService;

public RepresentanteController(RepresentanteService representanteService) {
this.representanteService = representanteService;
}

@GetMapping("/boilerplate")
public String boilerplate() {
return representanteService.status();
}
}
