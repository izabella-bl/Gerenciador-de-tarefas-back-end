package com.gerenciador.tarefas.service;

import com.gerenciador.tarefas.model.Afazeres;
import com.gerenciador.tarefas.model.Historico;
import com.gerenciador.tarefas.repository.HistoricoRespository;
import com.gerenciador.tarefas.service.HistoricoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class HistoricoServiceTest {

    private HistoricoService historicoService;

    @Mock
    private HistoricoRespository historicoRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        historicoService = new HistoricoService();
        historicoService.repository = historicoRepository;
    }

    @Test
    public void testSalvarHistorico() {
        // Arrange
        Afazeres afazer = new Afazeres();
        afazer.setId(1L);
        afazer.setSituacao("pendente");
        String acao = "andamento";
        Historico historicoMock = Historico.builder().acao("Status da atividade atualizada para: andamento.")
                .data("05/06/2024")
                .afazeres(afazer)
                .build();

        when(historicoRepository.save(any())).thenReturn(historicoMock);


        historicoService.salvarHistorico(afazer, acao);


        verify(historicoRepository, times(1)).save(any());
    }

    @Test
    void testDeletarHistorico() {
        Long id = 1L;
        Historico h1 = Historico.builder().id(1L).build();
        Historico h2 = Historico.builder().id(2L).build();


        List<Historico> historicos = Arrays.asList(h1, h2);

        when(historicoRepository.findByIdAfazeres(id)).thenReturn(historicos);

        historicoService.deletarHistorico(id);

        verify(historicoRepository, times(1)).findByIdAfazeres(id);
        verify(historicoRepository, times(1)).deleteById(h1.getId());
        verify(historicoRepository, times(1)).deleteById(h2.getId());
    }
}
