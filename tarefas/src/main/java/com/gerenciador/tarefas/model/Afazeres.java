package com.gerenciador.tarefas.model;


import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
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

    @ApiModelProperty(value = "Verifica se possui prazo")
    private boolean isPrazo;

    @ApiModelProperty(value = "Data de prazo")
    private String dataPrazo;

    @ApiModelProperty(value = "Verifica o grau da tarefa")
    private String grau;

    @ManyToOne
    private Usuario usuario;

    public Afazeres() {
    }


}
