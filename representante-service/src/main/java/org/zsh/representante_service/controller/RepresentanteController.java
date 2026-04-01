package org.zsh.representante_service.controller;

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
import org.zsh.representante_service.dto.AlocacaoRequest;
import org.zsh.representante_service.dto.AlocacaoResponse;
import org.zsh.representante_service.dto.RepresentanteDto;
import org.zsh.representante_service.dto.RepresentanteResponse;
import org.zsh.representante_service.service.RepresentanteService;

import java.util.List;

@RestController
@RequestMapping("/representantes")
@AllArgsConstructor
public class RepresentanteController {

    private final RepresentanteService representanteService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RepresentanteResponse cadastrar(@Valid @RequestBody RepresentanteDto dto) {
        return representanteService.cadastrar(dto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RepresentanteResponse> listarTodos() {
        return representanteService.listarTodos();
    }

    @GetMapping("/cpf/{cpf}")
    @ResponseStatus(HttpStatus.OK)
    public RepresentanteResponse buscarPorCpf(@PathVariable String cpf) {
        return representanteService.buscarPorCpf(cpf);
    }

    @GetMapping("/nome")
    @ResponseStatus(HttpStatus.OK)
    public List<RepresentanteResponse> buscarPorNome(@RequestParam("valor") String nome) {
        return representanteService.buscarPorNome(nome);
    }

    @PostMapping("/alocacoes")
    @ResponseStatus(HttpStatus.CREATED)
    public AlocacaoResponse alocarPeca(@Valid @RequestBody AlocacaoRequest request) {
        return representanteService.alocarPecaParaCliente(request);
    }

    @GetMapping("/alocacoes")
    @ResponseStatus(HttpStatus.OK)
    public List<AlocacaoResponse> listarTodasAlocacoes() {
        return representanteService.listarTodasAlocacoes();
    }
}
