package org.zsh.matricula_service.repository;

public interface IMatriculaRepository {
    void matricular(String disciplina, String horario, String aluno);
    void desmatricular(Long id);
}
