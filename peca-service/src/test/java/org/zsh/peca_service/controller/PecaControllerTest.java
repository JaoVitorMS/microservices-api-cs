package org.zsh.peca_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.zsh.peca_service.dto.PecaDto;
import org.zsh.peca_service.dto.PecaResponse;
import org.zsh.peca_service.service.PecaService;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PecaController.class)
class PecaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PecaService pecaService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Deve retornar 201 ao cadastrar peca valida")
    void deveRetornar201AoCadastrarPeca() throws Exception {
        PecaDto dto = new PecaDto(1L, "Peca Teste", "Descricao");
        PecaResponse response = new PecaResponse(1L, "Peca Teste", "Descricao");

        when(pecaService.cadastrar(any(PecaDto.class))).thenReturn(response);

        mockMvc.perform(post("/pecas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Peca Teste"));
    }

    @Test
    @DisplayName("Deve retornar 200 ao listar todas as pecas")
    void deveRetornar200AoListarTodas() throws Exception {
        PecaResponse response = new PecaResponse(1L, "Peca Teste", "Descricao");
        when(pecaService.listarTodos()).thenReturn(List.of(response));

        mockMvc.perform(get("/pecas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @Test
    @DisplayName("Deve retornar 400 se o corpo da requisicao for invalido")
    void deveRetornar400SeInvalido() throws Exception {
        PecaDto dtoInvalido = new PecaDto(null, "", "");

        mockMvc.perform(post("/pecas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dtoInvalido)))
                .andExpect(status().isBadRequest());
    }
}
