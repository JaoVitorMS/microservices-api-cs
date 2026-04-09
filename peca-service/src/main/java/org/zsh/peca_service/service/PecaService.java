package org.zsh.peca_service.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.zsh.peca_service.dto.PecaDto;
import org.zsh.peca_service.dto.PecaResponse;
import org.zsh.peca_service.model.PecaModel;
import org.zsh.peca_service.repository.PecaRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class PecaService {

    private final PecaRepository pecaRepository;

    public PecaResponse cadastrar(PecaDto dto) {
        if (pecaRepository.existsById(dto.id())) {
            throw new IllegalArgumentException("Numero de identificacao da peca ja cadastrado");
        }

        PecaModel peca = new PecaModel();
        peca.setId(dto.id());
        peca.setNome(dto.nome());
        peca.setDescricao(dto.descricao());

        return toResponse(pecaRepository.save(peca));
    }

    public List<PecaResponse> listarTodos() {
        return pecaRepository.findAll().stream().map(this::toResponse).toList();
    }

    public PecaResponse buscarPorId(Long id) {
        PecaModel peca = pecaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Peca nao encontrada para o id informado"));
        return toResponse(peca);
    }

    public List<PecaResponse> buscarPorNome(String nome) {
        return pecaRepository.findByNomeContainingIgnoreCase(nome)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private PecaResponse toResponse(PecaModel model) {
        return new PecaResponse(model.getId(), model.getNome(), model.getDescricao());
    }
}
