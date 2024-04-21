package com.gerenciador.tarefas.controller;

import com.gerenciador.tarefas.model.Afazeres;
import com.gerenciador.tarefas.repository.AfazeresRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class AfazeresControllerTest {

    @Mock
    private AfazeresRepository repository;

    @InjectMocks
    private AfazeresController controller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSalvarTarefa() {
        // Arrange
        Afazeres tarefa = new Afazeres();
        when(repository.save(tarefa)).thenReturn(tarefa);


       Long id = controller.idTarefaSalva(tarefa);

        verify(repository).save(tarefa);
    }

    @Test
    public void testBuscaIdTarefa() {

        Long id = 1L;
        Long idUsuario = 1L;
        Afazeres afazeres = new Afazeres();
        when(repository.findByIdUsuario(id, idUsuario)).thenReturn(Optional.of(afazeres));

        Optional<Afazeres> result = controller.buscaIdTarefa(id, idUsuario);

        assertEquals(afazeres, result.get());
        verify(repository).findByIdUsuario(id, idUsuario);
    }

    @Test
    public void testListaSituacao() {
        String situacao = "Em andamento";
        Long id = 1L;
        List<Afazeres> afazeresList = new ArrayList<>();
        when(repository.findBySituacao(situacao, id)).thenReturn(afazeresList);

        List<Afazeres> result = controller.listaSituacao(situacao, id);

        assertEquals(afazeresList, result);
        verify(repository).findBySituacao(situacao, id);
    }

    @Test
    public void testDeletarTarefa() {

        Long id = 1L;
        Long idUsuario = 1L;

        ResponseEntity<?> responseEntity = controller.deletarTarefa(id, idUsuario);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(repository).deleteByIdAndUsuarioId(id, idUsuario);
    }

    @Test
    public void testAtualizarTarefa() {

        Afazeres tarefa = new Afazeres();
        when(repository.save(tarefa)).thenReturn(tarefa);

        ResponseEntity<?> responseEntity = controller.atualizarTarefa(tarefa);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(repository).save(tarefa);
    }
}