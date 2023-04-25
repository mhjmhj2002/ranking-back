package com.mhj.ranking.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class EquipeModel {
    private Long id;
    private String nome;
//    private List<Classificacao> classificacaoList;
//    private List<Jogo> jogoList;
//    private List<Jogo> jogoList1;
    private PaisModel idPais;

}
