package com.gerenciador.tarefas.controller;

import com.gerenciador.tarefas.model.Historico;
import com.gerenciador.tarefas.repository.HistoricoRespository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class HistoricoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private HistoricoRespository historicoRespository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        HistoricoController historicoController = new HistoricoController();
        historicoController.historicoRespository = historicoRespository;
        mockMvc = MockMvcBuilders.standaloneSetup(historicoController).build();
    }

    @Test
    public void testBuscaHistorico() throws Exception {
        // Arrange
        Long afazeresId = 1L;
        Historico historico1 = new Historico();
        Historico historico2 = new Historico();
        List<Historico> historicos = new ArrayList<>();
        historicos.add(historico1);
        historicos.add(historico2);


        when(historicoRespository.findByIdAfazeres(afazeresId)).thenReturn(historicos);


        mockMvc.perform(get("/historico/{id}", afazeresId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0]").exists())
                .andExpect(jsonPath("$[1]").exists());

        verify(historicoRespository, times(1)).findByIdAfazeres(afazeresId);
    }
}
