package org.zsh.estudante_service.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.zsh.estudante_service.model.Estudante;

import java.util.List;

@Repository
@AllArgsConstructor
public class EstudanteRepository implements IEstudanteRepository{

    private IEstudanteJPARepository jpaRepository;

    @Override
    public Estudante criar ( Estudante estudante ) {
        return jpaRepository.save(estudante);
    }

    @Override
    public void deletar ( Long id ) {
        jpaRepository.deleteById(id);
    }

    @Override
    public Estudante consultarPorMatricula ( Long matricula ) {
        return jpaRepository.findByMatriculaId(matricula);
    }

    @Override
    public List<Estudante> consultarPorNome ( String nome ) {
        return jpaRepository.findByNomeContaining(nome);
    }
}
