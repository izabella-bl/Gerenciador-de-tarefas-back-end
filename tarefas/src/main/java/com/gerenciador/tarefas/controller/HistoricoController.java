package com.gerenciador.tarefas.controller;

import com.gerenciador.tarefas.model.Afazeres;
import com.gerenciador.tarefas.model.Historico;
import com.gerenciador.tarefas.repository.HistoricoRespository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class HistoricoController {

    @Autowired
    HistoricoRespository historicoRespository;

    @ApiOperation(value = "Busca o historica, pelo ID da tarefa")
    @GetMapping("/historico/{id}")
    public List<Historico> buscaHistorico(@PathVariable Long id) {
        return historicoRespository.findByIdAfazeres(id);
    }

}
