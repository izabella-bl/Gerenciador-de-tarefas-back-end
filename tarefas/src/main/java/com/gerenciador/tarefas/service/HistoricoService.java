package com.gerenciador.tarefas.service;

import com.gerenciador.tarefas.model.Afazeres;
import com.gerenciador.tarefas.model.Historico;
import com.gerenciador.tarefas.repository.HistoricoRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class HistoricoService {

    @Autowired
    public HistoricoRespository repository;

    public void salvarHistorico(Afazeres afazer, String acao){
        LocalDate dataAtual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = dataAtual.format(formatter);

        acao = acao.equals("S") || acao.equals("salva") ? verificaSituacao(acao):verificaSituacao(afazer.getSituacao());

        Historico historico = Historico.builder()
                                       .acao(acao)
                                       .data(dataFormatada)
                                       .afazeres(afazer).build();

        repository.save(historico);

    }

    public String verificaSituacao( String acao){

        String retorno = switch (acao){
            case "andamento" -> "Status da atividade atualizada para: andamento.";
            case "finalizada" -> "Status da atividade atualizada para: finalizada.";
            case "salva" -> "Atividade salva com sucesso.";
            case "S" -> "Descrição de atividade atualizadas.";
            default -> "";
        };

        return  retorno;
    }

    public void deletarHistorico(Long id){
        List<Historico> historicos = repository.findByIdAfazeres(id);

        for (Historico h: historicos) {
            repository.deleteById(h.getId());
        }
    }

}
