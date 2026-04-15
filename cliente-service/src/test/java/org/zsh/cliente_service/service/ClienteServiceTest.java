package org.zsh.cliente_service.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.zsh.cliente_service.dto.ClienteDto;
import org.zsh.cliente_service.dto.ClienteResponse;
import org.zsh.cliente_service.model.ClienteModel;
import org.zsh.cliente_service.repository.ClienteRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    private ClienteDto clienteDto;
    private ClienteModel clienteModel;

    @BeforeEach
    void setUp() {
        clienteDto = new ClienteDto("12345678901", "Cliente Teste");
        clienteModel = new ClienteModel(1L, "12345678901", "Cliente Teste");
    }

    @Test
    @DisplayName("Deve cadastrar um cliente com sucesso")
    void deveCadastrarClienteComSucesso() {
        when(clienteRepository.findByCpf("12345678901")).thenReturn(Optional.empty());
        when(clienteRepository.save(any(ClienteModel.class))).thenReturn(clienteModel);

        ClienteResponse response = clienteService.cadastrar(clienteDto);

        assertNotNull(response);
        assertEquals(clienteDto.cpf(), response.cpf());
        assertEquals(clienteDto.nome(), response.nome());
        verify(clienteRepository, times(1)).save(any(ClienteModel.class));
    }

    @Test
    @DisplayName("Deve lancar excecao ao cadastrar cliente com CPF ja existente")
    void deveLancarExcecaoAoCadastrarCpfExistente() {
        when(clienteRepository.findByCpf("12345678901")).thenReturn(Optional.of(clienteModel));

        assertThrows(IllegalArgumentException.class, () -> clienteService.cadastrar(clienteDto));
        verify(clienteRepository, never()).save(any(ClienteModel.class));
    }

    @Test
    @DisplayName("Deve buscar cliente por CPF com sucesso")
    void deveBuscarClientePorCpfComSucesso() {
        when(clienteRepository.findByCpf("12345678901")).thenReturn(Optional.of(clienteModel));

        ClienteResponse response = clienteService.buscarPorCpf("12345678901");

        assertNotNull(response);
        assertEquals("12345678901", response.cpf());
        verify(clienteRepository, times(1)).findByCpf("12345678901");
    }
}
