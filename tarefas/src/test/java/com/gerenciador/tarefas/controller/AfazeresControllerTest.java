package com.gerenciador.tarefas.controller;

import com.gerenciador.tarefas.model.Afazeres;
import com.gerenciador.tarefas.service.HistoricoService;
import com.gerenciador.tarefas.repository.AfazeresRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class AfazeresControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AfazeresRepository afazeresRepository;

    @Mock
    private HistoricoService historicoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        AfazeresController afazeresController = new AfazeresController();
        afazeresController.repository = afazeresRepository;
        afazeresController.historicoService = historicoService;
        mockMvc = MockMvcBuilders.standaloneSetup(afazeresController).build();
    }

    @Test
    public void testIdTarefaSalva() throws Exception {
        // Arrange
        Afazeres tarefa = new Afazeres();
        tarefa.setId(1L);

        when(afazeresRepository.save(any(Afazeres.class))).thenReturn(tarefa);

        // Act & Assert
        mockMvc.perform(post("/tarefa/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"titulo\":\"Tarefa 1\",\"descricao\":\"Descricao da tarefa\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));

        verify(afazeresRepository, times(1)).save(any(Afazeres.class));
        verify(historicoService, times(1)).salvarHistorico(any(Afazeres.class), eq("salva"));
    }

    @Test
    public void testBuscaIdTarefa() throws Exception {
        // Arrange
        Afazeres tarefa = new Afazeres();
        tarefa.setId(1L);

        when(afazeresRepository.findByIdUsuario(1L, 1L)).thenReturn(Optional.of(tarefa));

        // Act & Assert
        mockMvc.perform(get("/tarefa/1")
                        .param("idUsuario", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

        verify(afazeresRepository, times(1)).findByIdUsuario(1L, 1L);
    }

    @Test
    public void testListaSituacao() throws Exception {
        // Arrange
        List<Afazeres> tarefas = new ArrayList<>();
        tarefas.add(new Afazeres());
        tarefas.add(new Afazeres());

        when(afazeresRepository.findBySituacao("pendente", 1L)).thenReturn(tarefas);

        // Act & Assert
        mockMvc.perform(get("/tarefa/situacao")
                        .param("situacao", "pendente")
                        .param("id", "1"))
                .andExpect(status().isOk());

        verify(afazeresRepository, times(1)).findBySituacao("pendente", 1L);
    }

    @Test
    public void testDeletarTarefa() throws Exception {
        // Act & Assert
        mockMvc.perform(delete("/tarefa/deletar")
                        .param("id", "1")
                        .param("idUsuario", "1"))
                .andExpect(status().isOk());

        verify(afazeresRepository, times(1)).deleteByIdAndUsuarioId(1L, 1L);
    }

    @Test
    public void testAtualizarTarefa() throws Exception {
        // Arrange
        Afazeres tarefa = new Afazeres();
        tarefa.setId(1L);

        // Act & Assert
        mockMvc.perform(post("/tarefa/atualizar")
                        .param("indicaAtualizarDados", "S")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"titulo\":\"Tarefa Atualizada\",\"descricao\":\"Descricao atualizada\"}"))
                .andExpect(status().isOk());

        verify(afazeresRepository, times(1)).save(any(Afazeres.class));
        verify(historicoService, times(1)).salvarHistorico(any(Afazeres.class), eq("S"));
    }
}
