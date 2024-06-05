package com.gerenciador.tarefas.repository;

import com.gerenciador.tarefas.model.Historico;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface HistoricoRespository extends CrudRepository<Historico,Long> {

    @Query("SELECT t FROM Historico t WHERE t.afazeres.id = ?1")
    List<Historico> findByIdAfazeres(Long idAfazeres);
}
