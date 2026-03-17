package org.zsh.curso_service.repository;

import org.zsh.curso_service.model.Curso;

import java.util.List;

public interface ICursoRepository {
    Curso salvar(Curso curso);
    void deletar(Long codigoId);

    Curso encontrarHorario(String horario);

    List<Curso> encontrarPorCodigo ( Long codigoId );

    List<Curso> encontrarPorDisciplina ( String disciplina );
}
