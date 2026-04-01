package org.zsh.representante_service.service;

import feign.FeignException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.zsh.representante_service.client.ClienteClient;
import org.zsh.representante_service.client.PecaClient;
import org.zsh.representante_service.dto.AlocacaoRequest;
import org.zsh.representante_service.dto.AlocacaoResponse;
import org.zsh.representante_service.dto.RepresentanteDto;
import org.zsh.representante_service.dto.RepresentanteResponse;
import org.zsh.representante_service.model.AlocacaoModel;
import org.zsh.representante_service.model.RepresentanteModel;
import org.zsh.representante_service.repository.AlocacaoRepository;
import org.zsh.representante_service.repository.RepresentanteRepository;

import java.time.Instant;
import java.util.List;

@Service
@AllArgsConstructor
public class RepresentanteService {

    private final RepresentanteRepository representanteRepository;
    private final AlocacaoRepository alocacaoRepository;
    private final ClienteClient clienteClient;
    private final PecaClient pecaClient;

    public RepresentanteResponse cadastrar(RepresentanteDto dto) {
        if (representanteRepository.findByCpf(dto.cpf()).isPresent()) {
            throw new IllegalArgumentException("CPF de representante ja cadastrado");
        }

        RepresentanteModel representante = new RepresentanteModel();
        representante.setCpf(dto.cpf());
        representante.setNome(dto.nome());

        return toResponse(representanteRepository.save(representante));
    }

    public List<RepresentanteResponse> listarTodos() {
        return representanteRepository.findAll().stream().map(this::toResponse).toList();
    }

    public RepresentanteResponse buscarPorCpf(String cpf) {
        RepresentanteModel model = representanteRepository.findByCpf(cpf)
                .orElseThrow(() -> new IllegalArgumentException("Representante nao encontrado para o CPF informado"));
        return toResponse(model);
    }

    public List<RepresentanteResponse> buscarPorNome(String nome) {
        return representanteRepository.findByNomeContainingIgnoreCase(nome)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public AlocacaoResponse alocarPecaParaCliente(AlocacaoRequest request) {
        representanteRepository.findByCpf(request.cpfRepresentante())
                .orElseThrow(() -> new IllegalArgumentException("Representante nao encontrado para o CPF informado"));

        try {
            clienteClient.buscarPorCpf(request.cpfCliente());
        } catch (FeignException e) {
            throw new IllegalArgumentException("Cliente nao encontrado para o CPF informado");
        }

        try {
            pecaClient.buscarPorId(request.idPeca());
        } catch (FeignException e) {
            throw new IllegalArgumentException("Peca nao encontrada para o id informado");
        }

        AlocacaoModel alocacao = new AlocacaoModel();
        alocacao.setCpfRepresentante(request.cpfRepresentante());
        alocacao.setCpfCliente(request.cpfCliente());
        alocacao.setIdPeca(request.idPeca());
        alocacao.setDataHora(Instant.now());

        AlocacaoModel salva = alocacaoRepository.save(alocacao);
        return new AlocacaoResponse(
                salva.getId(),
                salva.getCpfRepresentante(),
                salva.getCpfCliente(),
                salva.getIdPeca(),
                salva.getDataHora()
        );
    }

    private RepresentanteResponse toResponse(RepresentanteModel model) {
        return new RepresentanteResponse(model.getId(), model.getCpf(), model.getNome());
    }

    public List<AlocacaoResponse> listarTodasAlocacoes(){
        return alocacaoRepository.findAll()
                .stream()
                .map(this::toAlocacaoResponse)
                .toList();
    }

    private AlocacaoResponse toAlocacaoResponse(AlocacaoModel model){
        return new AlocacaoResponse(
                model.getId(),
                model.getCpfRepresentante(),
                model.getCpfCliente(),
                model.getIdPeca(),
                model.getDataHora()
        );
    }
}
