package org.zsh.representante_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.zsh.representante_service.dto.RepresentanteDto;
import org.zsh.representante_service.dto.RepresentanteResponse;
import org.zsh.representante_service.service.RepresentanteService;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RepresentanteController.class)
class RepresentanteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RepresentanteService representanteService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Deve retornar 201 ao cadastrar representante valido")
    void deveRetornar201AoCadastrarRepresentante() throws Exception {
        RepresentanteDto dto = new RepresentanteDto("12345678901", "Representante Teste");
        RepresentanteResponse response = new RepresentanteResponse(1L, "12345678901", "Representante Teste");

        when(representanteService.cadastrar(any(RepresentanteDto.class))).thenReturn(response);

        mockMvc.perform(post("/representantes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.cpf").value("12345678901"));
    }

    @Test
    @DisplayName("Deve retornar 200 ao listar todos os representantes")
    void deveRetornar200AoListarTodas() throws Exception {
        RepresentanteResponse response = new RepresentanteResponse(1L, "12345678901", "Representante Teste");
        when(representanteService.listarTodos()).thenReturn(List.of(response));

        mockMvc.perform(get("/representantes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @Test
    @DisplayName("Deve retornar 400 se o corpo da requisicao for invalido")
    void deveRetornar400SeInvalido() throws Exception {
        RepresentanteDto dtoInvalido = new RepresentanteDto("", "");

        mockMvc.perform(post("/representantes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dtoInvalido)))
                .andExpect(status().isBadRequest());
    }
}
