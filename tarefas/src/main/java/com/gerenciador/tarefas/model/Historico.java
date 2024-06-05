package com.gerenciador.tarefas.model;


import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Builder
@Table(name = "Historico")
public class Historico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "ID do historico")
    private Long id;

    @ApiModelProperty(value = "Ação feita pelo usuario")
    private String acao;

    @ApiModelProperty(value = "Data da ação")
    private String data;

    @ManyToOne
    private Afazeres afazeres;


    public Historico() {
    }

    @Builder
    public Historico(Long id, String acao, String data, Afazeres afazeres) {
        this.id = id;
        this.acao = acao;
        this.data = data;
        this.afazeres = afazeres;
    }
}
