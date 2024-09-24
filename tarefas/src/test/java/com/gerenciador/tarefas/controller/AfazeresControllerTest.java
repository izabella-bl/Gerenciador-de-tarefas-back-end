package com.gerenciador.tarefas.controller;

import com.gerenciador.tarefas.model.Afazeres;
import com.gerenciador.tarefas.model.DadosDto;
import com.gerenciador.tarefas.repository.AfazeresRepository;
import com.gerenciador.tarefas.service.AfazeresService;
import com.gerenciador.tarefas.service.HistoricoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AfazeresController.class)
public class AfazeresControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AfazeresRepository afazeresRepository;

    @MockBean
    private HistoricoService historicoService;

    @MockBean
    private AfazeresService afazeresService;

    private Afazeres tarefa;

    @BeforeEach
    public void setUp() {
        tarefa = new Afazeres();
        tarefa.setId(1L);
        tarefa.setTitulo("Nova Tarefa");
        tarefa.setDescricao("Descrição da nova tarefa");
        tarefa.setGrau("Moderado");
        tarefa.setDataPrazo(LocalDate.now().plusDays(1).toString());
    }

    @Test
    public void testSalvarTarefa() throws Exception {
        when(afazeresService.verificaData(any(Afazeres.class))).thenReturn("Moderado");
        when(afazeresRepository.save(any(Afazeres.class))).thenReturn(tarefa);
        doNothing().when(historicoService).salvarHistorico(any(Afazeres.class), any(String.class));

        mockMvc.perform(post("/tarefa/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"title\": \"Nova Tarefa\", \"description\": \"Descrição da nova tarefa\", \"dataPrazo\": \"2024-06-15\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(tarefa.getId()))
                .andExpect(jsonPath("$.grau").value(tarefa.getGrau()));
    }

    @Test
    public void testDeletarTarefa() throws Exception {
        doNothing().when(historicoService).deletarHistorico(1L);
        doNothing().when(afazeresRepository).deleteByIdAndUsuarioId(1L, 1L);

        mockMvc.perform(delete("/tarefa/deletar")
                        .param("id", "1")
                        .param("idUsuario", "1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testAtualizarTarefa() throws Exception {
        when(afazeresService.verificaData(any(Afazeres.class))).thenReturn("Moderado");
        when(afazeresRepository.save(any(Afazeres.class))).thenReturn(tarefa);
        doNothing().when(historicoService).salvarHistorico(any(Afazeres.class), any(String.class));

        mockMvc.perform(post("/tarefa/atualizar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"title\": \"Tarefa Atualizada\", \"description\": \"Descrição atualizada\", \"dataPrazo\": \"2024-06-15\" }")
                        .param("indicaAtualizarDados", "atualizar"))
                .andExpect(status().isOk());
    }
}
