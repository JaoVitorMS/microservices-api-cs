package org.zsh.estudante_service.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.zsh.estudante_service.dto.EstudanteDto;
import org.zsh.estudante_service.dto.EstudanteMapper;
import org.zsh.estudante_service.model.Estudante;
import org.zsh.estudante_service.repository.EstudanteRepository;
import java.util.List;

@Service
@AllArgsConstructor
public class EstudanteService {
    private EstudanteRepository repository;

    public EstudanteDto criar(EstudanteDto estudanteDto){
        Estudante estudanteD = EstudanteMapper.toDomain(estudanteDto);
        repository.criar(estudanteD);
        return  estudanteDto;
    }

    public EstudanteDto buscarPorMatricula ( Long matriculaId ) {
        Estudante estudante = repository.consultarPorMatricula(matriculaId);
        return EstudanteMapper.toDto(estudante);
    }

    public List<EstudanteDto> buscarPorNome (String nome) {
        List<Estudante> estudantes = repository.consultarPorNome(nome);
        return estudantes.stream()
                .map(EstudanteMapper::toDto)
                .toList();
    }

    public void deletar ( Long id ) {
        repository.deletar(id);
    }

}
