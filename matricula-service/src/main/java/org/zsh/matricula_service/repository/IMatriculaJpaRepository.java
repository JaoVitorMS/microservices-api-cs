package org.zsh.matricula_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zsh.matricula_service.domain.Matricula;

public interface IMatriculaJpaRepository extends JpaRepository<Matricula, Long> {
}
