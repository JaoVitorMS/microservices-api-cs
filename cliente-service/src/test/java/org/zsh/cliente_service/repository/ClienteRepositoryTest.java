package org.zsh.cliente_service.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.zsh.cliente_service.model.ClienteModel;
import org.zsh.cliente_service.repository.ClienteRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    @DisplayName("Deve salvar e encontrar um cliente por CPF")
    void deveSalvarEEncontrarPorCpf() {
        ClienteModel cliente = new ClienteModel(null, "11122233344", "Cliente BD");
        clienteRepository.save(cliente);

        Optional<ClienteModel> result = clienteRepository.findByCpf("11122233344");

        assertTrue(result.isPresent());
        assertEquals("Cliente BD", result.get().getNome());
    }

    @Test
    @DisplayName("Deve encontrar clientes por parte do nome")
    void deveEncontrarPorParteDoNome() {
        ClienteModel cliente1 = new ClienteModel(null, "111", "Joao Silva");
        ClienteModel cliente2 = new ClienteModel(null, "222", "Maria Silva");
        clienteRepository.save(cliente1);
        clienteRepository.save(cliente2);

        List<ClienteModel> result = clienteRepository.findByNomeContainingIgnoreCase("silva");

        assertEquals(2, result.size());
    }
}
