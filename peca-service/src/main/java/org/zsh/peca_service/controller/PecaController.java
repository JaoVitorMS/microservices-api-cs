package org.zsh.peca_service.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.zsh.peca_service.dto.PecaDto;
import org.zsh.peca_service.dto.PecaResponse;
import org.zsh.peca_service.service.PecaService;

import java.util.List;

@RestController
@RequestMapping("/pecas")
@AllArgsConstructor
public class PecaController {

    private final PecaService pecaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PecaResponse cadastrar(@Valid @RequestBody PecaDto dto) {
        return pecaService.cadastrar(dto);
    }

    @GetMapping
    public List<PecaResponse> listarTodos() {
        return pecaService.listarTodos();
    }

    @GetMapping("/{id}")
    public PecaResponse buscarPorId(@PathVariable Long id) {
        return pecaService.buscarPorId(id);
    }

    @GetMapping("/nome")
    public List<PecaResponse> buscarPorNome(@RequestParam("valor") String nome) {
        return pecaService.buscarPorNome(nome);
    }
}
