package org.zsh.cliente_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zsh.cliente_service.model.ClienteModel;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {

    Optional<ClienteModel> findByCpf(String cpf);

    List<ClienteModel> findByNomeContainingIgnoreCase(String nome);
}
