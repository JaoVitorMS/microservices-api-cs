package org.zsh.matricula_service.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zsh.matricula_service.controller.dto.MatricularRequest;
import org.zsh.matricula_service.domain.Disponivel;
import org.zsh.matricula_service.service.MatriculaService;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {

    private final MatriculaService matriculaService;

    public MatriculaController(MatriculaService matriculaService) {
        this.matriculaService = matriculaService;
    }

    @GetMapping("/disponiveis")
    public Disponivel listarDisponiveis() {
        return matriculaService.listarDisponiveis();
    }

    @PostMapping
    public ResponseEntity<Void> matricular(@Valid @RequestBody MatricularRequest request) {
        matriculaService.matricular(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

