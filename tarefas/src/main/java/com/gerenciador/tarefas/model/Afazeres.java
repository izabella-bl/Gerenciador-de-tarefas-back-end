package com.gerenciador.tarefas.model;


import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Builder
@Getter
@Entity
@Table(name = "tarefas")
public class Afazeres {


    @ApiModelProperty(value = "ID da tarefa")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;

    @ApiModelProperty(value = "Título da tarefa")
    private String titulo;

    @ApiModelProperty(value = "Descrição da tarefa")
    private String descricao;

    @ApiModelProperty(value = "Situação da tarefa")
    private String situacao;

    @ManyToOne
    private Usuario usuario;

    public Afazeres() {
    }


    @Builder
    public Afazeres(Long id, String titulo, String descricao, String situacao, Usuario usuario) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.situacao = situacao;
        this.usuario = usuario;
    }
}
