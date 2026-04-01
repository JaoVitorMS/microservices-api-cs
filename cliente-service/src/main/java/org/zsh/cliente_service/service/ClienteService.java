package org.zsh.cliente_service.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.zsh.cliente_service.dto.ClienteDto;
import org.zsh.cliente_service.dto.ClienteResponse;
import org.zsh.cliente_service.model.ClienteModel;
import org.zsh.cliente_service.repository.ClienteRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteResponse cadastrar(ClienteDto dto) {
        if (clienteRepository.findByCpf(dto.cpf()).isPresent()) {
            throw new IllegalArgumentException("CPF de cliente ja cadastrado");
        }

        ClienteModel cliente = new ClienteModel();
        cliente.setCpf(dto.cpf());
        cliente.setNome(dto.nome());
        ClienteModel salvo = clienteRepository.save(cliente);

        return toResponse(salvo);
    }

    public List<ClienteResponse> listarTodos() {
        return clienteRepository.findAll().stream().map(this::toResponse).toList();
    }

    public ClienteResponse buscarPorCpf(String cpf) {
        ClienteModel cliente = clienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new IllegalArgumentException("Cliente nao encontrado para o CPF informado"));
        return toResponse(cliente);
    }

    public List<ClienteResponse> buscarPorNome(String nome) {
        return clienteRepository.findByNomeContainingIgnoreCase(nome)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private ClienteResponse toResponse(ClienteModel model) {
        return new ClienteResponse(model.getId(), model.getCpf(), model.getNome());
    }
}
