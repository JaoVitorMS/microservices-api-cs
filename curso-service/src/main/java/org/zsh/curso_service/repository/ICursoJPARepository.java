package org.zsh.curso_service.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.zsh.curso_service.model.Curso;
import java.util.List;

public interface ICursoJPARepository extends JpaRepository<Curso,Long>{

    Curso findCursoByHorario ( String horario );

    List<Curso> findCursoByCodigoId ( Long codigoId );

    List<Curso> findCursoByDisciplina ( String disciplina );
}
