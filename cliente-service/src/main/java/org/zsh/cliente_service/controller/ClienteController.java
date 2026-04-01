package org.zsh.cliente_service.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import org.zsh.cliente_service.dto.ClienteDto;
import org.zsh.cliente_service.dto.ClienteResponse;
import org.zsh.cliente_service.service.ClienteService;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@AllArgsConstructor
@Tag(name = "Cliente", description = "Endpoints para gerenciamento de clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cadastrar um novo cliente", description = "Cria um novo cliente no sistema a partir dos dados fornecidos.")
    public ClienteResponse cadastrar(@Valid @RequestBody ClienteDto dto) {
        return clienteService.cadastrar(dto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    @Operation(summary = "Listar todos os clientes", description = "Retorna uma lista com todos os clientes cadastrados.")
    public List<ClienteResponse> listarTodos() {
        return clienteService.listarTodos();
    }

    @GetMapping("/cpf/{cpf}")
    @ResponseStatus(HttpStatus.FOUND)
    @Operation(summary = "Buscar cliente por CPF", description = "Retorna os detalhes de um cliente específico através do seu CPF.")
    public ClienteResponse buscarPorCpf(@PathVariable String cpf) {
        return clienteService.buscarPorCpf(cpf);
    }

    @GetMapping("/nome")
    @ResponseStatus(HttpStatus.FOUND)
    @Operation(summary = "Buscar clientes por nome", description = "Retorna uma lista de clientes cujo nome contém o valor informado.")
    public List<ClienteResponse> buscarPorNome(@RequestParam("valor") String nome) {
        return clienteService.buscarPorNome(nome);
    }
}
