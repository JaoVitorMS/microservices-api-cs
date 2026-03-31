package org.zsh.peca_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zsh.peca_service.service.PecaService;

@RestController
@RequestMapping("/pecas")
public class PecaController {

private final PecaService pecaService;

public PecaController(PecaService pecaService) {
this.pecaService = pecaService;
}

@GetMapping("/boilerplate")
public String boilerplate() {
return pecaService.status();
}
}
