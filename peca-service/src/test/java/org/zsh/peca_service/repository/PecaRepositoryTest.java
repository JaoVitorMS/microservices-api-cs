package org.zsh.peca_service.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.zsh.peca_service.model.PecaModel;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PecaRepositoryTest {

    @Autowired
    private PecaRepository pecaRepository;

    @Test
    @DisplayName("Deve salvar e encontrar uma peca por ID")
    void deveSalvarEEncontrarPorId() {
        PecaModel peca = new PecaModel(10L, "Peca BD", "Descricao BD");
        pecaRepository.save(peca);

        Optional<PecaModel> result = pecaRepository.findById(10L);

        assertTrue(result.isPresent());
        assertEquals("Peca BD", result.get().getNome());
    }

    @Test
    @DisplayName("Deve encontrar pecas por parte do nome")
    void deveEncontrarPorParteDoNome() {
        PecaModel peca1 = new PecaModel(1L, "Motor V8", "Motor potente");
        PecaModel peca2 = new PecaModel(2L, "Filtro de Ar", "Filtro");
        pecaRepository.save(peca1);
        pecaRepository.save(peca2);

        List<PecaModel> result = pecaRepository.findByNomeContainingIgnoreCase("motor");

        assertEquals(1, result.size());
        assertEquals("Motor V8", result.get(0).getNome());
    }
}
