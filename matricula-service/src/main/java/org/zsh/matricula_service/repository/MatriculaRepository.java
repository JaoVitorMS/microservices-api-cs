package org.zsh.matricula_service.repository;

import org.springframework.stereotype.Repository;
import org.zsh.matricula_service.domain.Matricula;

@Repository

public class MatriculaRepository implements IMatriculaRepository {

    private final IMatriculaJpaRepository jpaRepository;

    public MatriculaRepository(IMatriculaJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void matricular(String disciplina, String horario, String aluno) {
        Matricula matricula = Matricula.builder().disciplina(disciplina).horario(horario).aluno(aluno).build();
        jpaRepository.save(matricula);
    }

    @Override
    public void desmatricular(Long id) {
        jpaRepository.deleteById(id);
    }
}
