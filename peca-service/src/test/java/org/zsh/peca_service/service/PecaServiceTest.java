package org.zsh.peca_service.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.zsh.peca_service.dto.PecaDto;
import org.zsh.peca_service.dto.PecaResponse;
import org.zsh.peca_service.model.PecaModel;
import org.zsh.peca_service.repository.PecaRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PecaServiceTest {

    @Mock
    private PecaRepository pecaRepository;

    @InjectMocks
    private PecaService pecaService;

    private PecaDto pecaDto;
    private PecaModel pecaModel;

    @BeforeEach
    void setUp() {
        pecaDto = new PecaDto(1L, "Peca Teste", "Descricao Teste");
        pecaModel = new PecaModel(1L, "Peca Teste", "Descricao Teste");
    }

    @Test
    @DisplayName("Deve cadastrar uma peca com sucesso")
    void deveCadastrarPecaComSucesso() {
        when(pecaRepository.existsById(1L)).thenReturn(false);
        when(pecaRepository.save(any(PecaModel.class))).thenReturn(pecaModel);

        PecaResponse response = pecaService.cadastrar(pecaDto);

        assertNotNull(response);
        assertEquals(pecaDto.id(), response.id());
        assertEquals(pecaDto.nome(), response.nome());
        verify(pecaRepository, times(1)).save(any(PecaModel.class));
    }

    @Test
    @DisplayName("Deve lancar excecao ao cadastrar peca com ID ja existente")
    void deveLancarExcecaoAoCadastrarIdExistente() {
        when(pecaRepository.existsById(1L)).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> pecaService.cadastrar(pecaDto));
        verify(pecaRepository, never()).save(any(PecaModel.class));
    }

    @Test
    @DisplayName("Deve buscar peca por ID com sucesso")
    void deveBuscarPecaPorIdComSucesso() {
        when(pecaRepository.findById(1L)).thenReturn(Optional.of(pecaModel));

        PecaResponse response = pecaService.buscarPorId(1L);

        assertNotNull(response);
        assertEquals(1L, response.id());
        verify(pecaRepository, times(1)).findById(1L);
    }
}
