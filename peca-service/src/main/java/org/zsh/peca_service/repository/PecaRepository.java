package org.zsh.peca_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zsh.peca_service.model.PecaModel;

import java.util.List;

public interface PecaRepository extends JpaRepository<PecaModel, Long> {

    List<PecaModel> findByNomeContainingIgnoreCase(String nome);
}
