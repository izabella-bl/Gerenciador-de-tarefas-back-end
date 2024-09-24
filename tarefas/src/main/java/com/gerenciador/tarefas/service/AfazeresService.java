package com.gerenciador.tarefas.service;

import com.gerenciador.tarefas.model.Afazeres;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class AfazeresService {

    public String verificaData(Afazeres afazeres) {
        String retorno = "";

        if (afazeres.isPrazo() && afazeres.getDataPrazo().length() == 10) {

                String dia = afazeres.getDataPrazo().substring(0, 2);
                String mes = afazeres.getDataPrazo().substring(3, 5);
                String ano = afazeres.getDataPrazo().substring(6);

                LocalDate hoje = LocalDate.now();


                int  diaHoje = hoje.getDayOfMonth();

                int diasRestantes = Integer.parseInt(dia) - diaHoje;


                if (diasRestantes == 0) {
                    retorno = "Hoje";
                } else if (diasRestantes >= 4 && diasRestantes < 6) {
                    retorno = "Medio";
                } else if (diasRestantes <= 3 && diasRestantes > 0) {
                    retorno = "Alto";
                } else if (diasRestantes < 0) {
                    retorno = "Grave";
                } else {
                    retorno = "Baixo";
                }

            System.out.println(retorno);

        }

        return retorno;
    }

   public String formataData(String dataString){

       LocalDate dataAtual = LocalDate.parse(dataString);
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
       String dataFormatada = dataAtual.format(formatter);

       return dataFormatada;
   }

}
