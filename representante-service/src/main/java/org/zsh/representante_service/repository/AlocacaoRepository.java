package org.zsh.representante_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zsh.representante_service.model.AlocacaoModel;

public interface AlocacaoRepository extends JpaRepository<AlocacaoModel, Long> {
}
