package com.mhj.ranking.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TemporadaModel {
    private Long id;
    private String nome;
    private int anoInicio;
    private int anoFim;
    private TorneioModel idTorneio;
}
