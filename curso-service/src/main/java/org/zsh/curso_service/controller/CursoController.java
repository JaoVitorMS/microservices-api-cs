package org.zsh.curso_service.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zsh.curso_service.dto.CursoDto;
import org.zsh.curso_service.service.CursoService;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/cursos")
public class CursoController {
    private final CursoService cursoService;

    @PostMapping("/criar")
    public ResponseEntity<CursoDto> criarCurso(@RequestBody CursoDto cursoDto) {
        CursoDto cursoCriado = cursoService.cadastrarCurso(cursoDto);
        return ResponseEntity.ok(cursoCriado);
    }

    @GetMapping("/encontrar-horario/{horario}")
    public ResponseEntity<CursoDto> encontrarHorario(@PathVariable String horario) {
        var horarioR = cursoService.encontrarPorHorario(horario);
        return ResponseEntity.ok(horarioR);
    }

    @GetMapping("/encontrar-codigo/{codigoId}")
    public ResponseEntity<List<CursoDto>> encontrarCodigo(@PathVariable Long codigoId) {
        var codigoR = cursoService.encontrarPorCodigo(codigoId);
        return ResponseEntity.ok(codigoR);
    }

    @GetMapping("/encontrar-disciplina/{disciplina}")
    public ResponseEntity<List<CursoDto>> encontrarDisciplina(@PathVariable String disciplina) {
        var disciplinaR = cursoService.encontrarPorDisciplina(disciplina);
        return ResponseEntity.ok(disciplinaR);
    }

    @GetMapping("/encontrar-todos")
    public ResponseEntity<List<CursoDto>> encontrarTodos() {
        var todosR = cursoService.encontrarTodos();
        return ResponseEntity.ok(todosR);
    }
}
