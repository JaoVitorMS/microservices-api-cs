package org.zsh.representante_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zsh.representante_service.model.RepresentanteModel;

import java.util.List;
import java.util.Optional;

public interface RepresentanteRepository extends JpaRepository<RepresentanteModel, Long> {

    Optional<RepresentanteModel> findByCpf(String cpf);

    List<RepresentanteModel> findByNomeContainingIgnoreCase(String nome);
}
