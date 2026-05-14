package org.zsh.representante_service.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.zsh.representante_service.model.RepresentanteModel;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class RepresentanteRepositoryTest {

    @Autowired
    private RepresentanteRepository representanteRepository;

    @Test
    @DisplayName("Deve salvar e encontrar um representante por CPF")
    void deveSalvarEEncontrarPorCpf() {
        RepresentanteModel representante = new RepresentanteModel(null, "11122233344", "Representante BD");
        representanteRepository.save(representante);

        Optional<RepresentanteModel> result = representanteRepository.findByCpf("11122233344");

        assertTrue(result.isPresent());
        assertEquals("Representante BD", result.get().getNome());
    }

    @Test
    @DisplayName("Deve encontrar representantes por parte do nome")
    void deveEncontrarPorParteDoNome() {
        RepresentanteModel rep1 = new RepresentanteModel(null, "111", "Representante A");
        RepresentanteModel rep2 = new RepresentanteModel(null, "222", "Outro B");
        representanteRepository.save(rep1);
        representanteRepository.save(rep2);

        List<RepresentanteModel> result = representanteRepository.findByNomeContainingIgnoreCase("representante");

        assertEquals(1, result.size());
        assertEquals("Representante A", result.get(0).getNome());
    }
}
