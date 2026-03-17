package org.zsh.curso_service.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.zsh.curso_service.model.Curso;

import java.util.List;

@Repository
@AllArgsConstructor
public class CursoRepository implements ICursoRepository{

    private ICursoJPARepository repository;

    @Override
    public Curso salvar ( Curso curso ) {
        return repository.save(curso);
    }

    @Override
    public void deletar ( Long codigoId ) {
        repository.deleteById(codigoId);
    }

    @Override
    public Curso encontrarHorario ( String horario ) {
        return repository.findCursoByHorario(horario);
    }

    @Override
    public List<Curso> encontrarPorCodigo ( Long codigoId ) {
        return repository.findCursoByCodigoId(codigoId);
    }

    @Override
    public List<Curso> encontrarPorDisciplina ( String disciplina ) {
        return repository.findCursoByDisciplina(disciplina);
    }

    public List<Curso> encontrarTodos() {
        return repository.findAll();
    }
}
