package com.gerenciador.tarefas.repository;

import com.gerenciador.tarefas.model.Afazeres;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface AfazeresRepository extends CrudRepository<Afazeres, Long> {

    @Query("SELECT t FROM Afazeres t INNER JOIN t.usuario u WHERE t.situacao = ?1 AND u.id = ?2")
    List<Afazeres> findBySituacao(String situacao, Long idUsuario);

    @Query("SELECT t FROM Afazeres t WHERE t.id = ?1 AND t.usuario.id = ?2")
    Optional<Afazeres> findByIdUsuario(Long idTarefa, Long idUsuario);

    @Modifying
    @Transactional
    @Query("DELETE FROM Afazeres t WHERE t.id = ?1 AND t.usuario.id = ?2")
    void deleteByIdAndUsuarioId(Long idTarefa, Long idUsuario);
}