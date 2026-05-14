package org.zsh.cliente_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.zsh.cliente_service.dto.ClienteDto;
import org.zsh.cliente_service.dto.ClienteResponse;
import org.zsh.cliente_service.service.ClienteService;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClienteController.class)
class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ClienteService clienteService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Deve retornar 201 ao cadastrar cliente valido")
    void deveRetornar201AoCadastrarCliente() throws Exception {
        ClienteDto dto = new ClienteDto("12345678901", "Cliente Teste");
        ClienteResponse response = new ClienteResponse(1L, "12345678901", "Cliente Teste");

        when(clienteService.cadastrar(any(ClienteDto.class))).thenReturn(response);

        mockMvc.perform(post("/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.cpf").value("12345678901"));
    }

    @Test
    @DisplayName("Deve retornar 200 ao listar todos os clientes")
    void deveRetornar200AoListarTodas() throws Exception {
        ClienteResponse response = new ClienteResponse(1L, "12345678901", "Cliente Teste");
        when(clienteService.listarTodos()).thenReturn(List.of(response));

        mockMvc.perform(get("/clientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @Test
    @DisplayName("Deve retornar 400 se o corpo da requisicao for invalido")
    void deveRetornar400SeInvalido() throws Exception {
        ClienteDto dtoInvalido = new ClienteDto("", "");

        mockMvc.perform(post("/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dtoInvalido)))
                .andExpect(status().isBadRequest());
    }
}
