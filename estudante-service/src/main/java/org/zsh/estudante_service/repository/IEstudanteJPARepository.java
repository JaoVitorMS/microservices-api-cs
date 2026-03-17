package org.zsh.estudante_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zsh.estudante_service.model.Estudante;

import java.util.List;

public interface IEstudanteJPARepository extends JpaRepository<Estudante, Long> {
    Estudante findByMatriculaId ( Long matriculaId );

    List<Estudante> findByNomeContaining ( String nome );
    
}