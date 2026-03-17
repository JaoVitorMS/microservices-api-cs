package org.zsh.estudante_service.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zsh.estudante_service.dto.EstudanteDto;
import org.zsh.estudante_service.service.EstudanteService;
import java.util.List;

@RestController
@RequestMapping("/estudantes")
@AllArgsConstructor
public class EstudanteController {
    private final EstudanteService estudanteService;

    @PostMapping("/cadastrar")
    public ResponseEntity<EstudanteDto> cadastrarEstudante(@RequestBody EstudanteDto estudanteDto) {
        EstudanteDto estudanteCadastrado = estudanteService.criar(estudanteDto);
        return ResponseEntity.ok(estudanteCadastrado);
    }

    @GetMapping("/consultarPorMatricula/{matricula}")
    public ResponseEntity<EstudanteDto> consultarPorMatricula(@PathVariable Long matricula) {
        EstudanteDto estudante = estudanteService.buscarPorMatricula(matricula);
            return ResponseEntity.ok(estudante);
    }

    @GetMapping("/consultarPorNome/{nome}")
    public ResponseEntity<List<EstudanteDto>> consultarPorNome(@PathVariable String nome) {
        List<EstudanteDto> estudantes = estudanteService.buscarPorNome(nome);
        return ResponseEntity.ok(estudantes);
    }
}
