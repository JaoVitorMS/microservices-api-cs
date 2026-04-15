package org.zsh.representante_service.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.zsh.representante_service.client.ClienteClient;
import org.zsh.representante_service.client.PecaClient;
import org.zsh.representante_service.dto.RepresentanteDto;
import org.zsh.representante_service.dto.RepresentanteResponse;
import org.zsh.representante_service.model.RepresentanteModel;
import org.zsh.representante_service.repository.AlocacaoRepository;
import org.zsh.representante_service.repository.RepresentanteRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RepresentanteServiceTest {

    @Mock
    private RepresentanteRepository representanteRepository;

    @Mock
    private AlocacaoRepository alocacaoRepository;

    @Mock
    private ClienteClient clienteClient;

    @Mock
    private PecaClient pecaClient;

    @InjectMocks
    private RepresentanteService representanteService;

    private RepresentanteDto representanteDto;
    private RepresentanteModel representanteModel;

    @BeforeEach
    void setUp() {
        representanteDto = new RepresentanteDto("12345678901", "Representante Teste");
        representanteModel = new RepresentanteModel(1L, "12345678901", "Representante Teste");
    }

    @Test
    @DisplayName("Deve cadastrar um representante com sucesso")
    void deveCadastrarRepresentanteComSucesso() {
        when(representanteRepository.findByCpf("12345678901")).thenReturn(Optional.empty());
        when(representanteRepository.save(any(RepresentanteModel.class))).thenReturn(representanteModel);

        RepresentanteResponse response = representanteService.cadastrar(representanteDto);

        assertNotNull(response);
        assertEquals(representanteDto.cpf(), response.cpf());
        assertEquals(representanteDto.nome(), response.nome());
        verify(representanteRepository, times(1)).save(any(RepresentanteModel.class));
    }

    @Test
    @DisplayName("Deve lancar excecao ao cadastrar representante com CPF ja existente")
    void deveLancarExcecaoAoCadastrarCpfExistente() {
        when(representanteRepository.findByCpf("12345678901")).thenReturn(Optional.of(representanteModel));

        assertThrows(IllegalArgumentException.class, () -> representanteService.cadastrar(representanteDto));
        verify(representanteRepository, never()).save(any(RepresentanteModel.class));
    }

    @Test
    @DisplayName("Deve buscar representante por CPF com sucesso")
    void deveBuscarRepresentantePorCpfComSucesso() {
        when(representanteRepository.findByCpf("12345678901")).thenReturn(Optional.of(representanteModel));

        RepresentanteResponse response = representanteService.buscarPorCpf("12345678901");

        assertNotNull(response);
        assertEquals("12345678901", response.cpf());
        verify(representanteRepository, times(1)).findByCpf("12345678901");
    }
}
