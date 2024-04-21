package com.gerenciador.tarefas.controller;


import com.gerenciador.tarefas.model.Afazeres;
import com.gerenciador.tarefas.repository.AfazeresRepository;
import com.gerenciador.tarefas.repository.UsuarioRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AfazeresController {

    @Autowired
    public AfazeresRepository repository;

    @Autowired
    public UsuarioRepository usuarioRepository;

    @ApiOperation(value = "Salva a tarefa do usurio e retorna o seu Id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Tarefa salva com sucesso"),
    })
    @PostMapping("tarefa/save")
    public Long idTarefaSalva (@RequestBody Afazeres tarefa) {
        Afazeres tarefaSalva = repository.save(tarefa);
        return tarefaSalva.getId();
    }


    @ApiOperation(value = "Busca a tarefa do usuario, pelo ID da tarefa")
    @GetMapping("/tarefa/{id}")
    public Optional<Afazeres> buscaIdTarefa(@PathVariable Long id , @RequestParam Long idUsuario) {
        Optional<Afazeres> tarefa = repository.findByIdUsuario(id,idUsuario);
        return tarefa;
    }

    @ApiOperation(value = "Busca as tarefas do usuario, pela situação")
    @GetMapping("/tarefa/situacao")
    public List<Afazeres> listaSituacao(@RequestParam String situacao,@RequestParam Long id) {
       return repository.findBySituacao(situacao,id);
    }

    @ApiOperation(value = "Deleta a tarefa pelo ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Tarefa deletada com sucesso"),
    })
    @DeleteMapping("tarefa/deletar")
    public ResponseEntity<?> deletarTarefa(@RequestParam Long id,@RequestParam Long idUsuario) {
        repository.deleteByIdAndUsuarioId(id, idUsuario);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "Atualizar dados da tarefa do usurio")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Tarefa atulizada com sucesso"),
    })
    @PostMapping("tarefa/atualizar")
    public ResponseEntity<?> atualizarTarefa (@RequestBody Afazeres tarefa) {
        repository.save(tarefa);
        return new ResponseEntity(HttpStatus.OK);
    }




}
